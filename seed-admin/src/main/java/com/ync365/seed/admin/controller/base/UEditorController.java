package com.ync365.seed.admin.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ync365.seed.admin.controller.ueditor.ActionEnter;
import com.ync365.seed.admin.utils.UpYunFileManager;
import com.ync365.seed.commons.mapper.JsonMapper;

@Controller
@RequestMapping("/ueditor")
public class UEditorController {

	@Autowired
	private UpYunFileManager upYunFileManager;
    
	/**
	 * ueditor 获取配置文件
	 * @param request
	 * @return
	 */
	@RequestMapping("/config")
	@ResponseBody
	public Object getConfigJson(HttpServletRequest request) {
		StringBuffer buffer = new StringBuffer(request.getServletContext()
				.getRealPath("/"));
		buffer.append("\\resources\\ueditor\\config\\config.json");
		String str = new ActionEnter(request, buffer.toString()).exec();
		JsonMapper mapper = new JsonMapper() ;
		Map map = mapper.fromJson(str, Map.class);
		// 根据环境变量替换图片服务器地址
		if (map != null) {
			map.put("imageUrlPrefix", upYunFileManager.getFileServerUrl());
		}
		return map;
	}

	/**
	 * unicode 转 汉字
	 * 
	 * @param utfString
	 * @return
	 */
	public String convert(String utfString) {
		StringBuilder sb = new StringBuilder();
		int i = -1;
		int pos = 0;

		while ((i = utfString.indexOf("\\u", pos)) != -1) {
			sb.append(utfString.substring(pos, i));
			if (i + 5 < utfString.length()) {
				pos = i + 6;
				sb.append((char) Integer.parseInt(
						utfString.substring(i + 2, i + 6), 16));
			}
		}

		return sb.toString();
	}

	/**
     * ueditor 上传文件
     * @param request
     * @return
     */
	@RequestMapping("/uploadImage")
	public void upImg(
			HttpServletResponse response,
			HttpServletRequest request,
			@RequestParam(value = "uploaderImage", required = false) MultipartFile file,
			Integer merchantId) {
		byte[] bytes;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String suffix = file.getOriginalFilename().substring(
					file.getOriginalFilename().lastIndexOf("."));
			bytes = file.getBytes();
			String fileName = merchantId + "_" + System.currentTimeMillis()
					+ suffix;
			String filePath = "/images/p/" + merchantId + "/" + fileName;
			// 上传
			boolean flag = upYunFileManager.writeFile(filePath, bytes, null);
			if (flag) {
				map.put("state", "SUCCESS");
			}
			map.put("url", filePath);
			map.put("title", fileName);
			map.put("original", fileName);
			JsonMapper mapper = new JsonMapper();
			response.setContentType("text/html");
			response.getWriter().write(mapper.toJson(map));
			response.flushBuffer();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
     * ueditor 图片管理
     * @param request
     * @return
     */
	@RequestMapping("/listimage")
	public void listImg(HttpServletResponse response,
			HttpServletRequest request, Integer start, Integer size,
			Integer merchantId) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			String filePath = "/images/p/" + merchantId + "/";
			// 上传
			List<Map<String, Object>> list = upYunFileManager.readDir(filePath);
			map = changeListToMap(list, start + 1, size, filePath);
			map.put("start", (start + 1) * size);
			response.setContentType("text/html");
			JsonMapper mapper = new JsonMapper();
			response.getWriter().write(mapper.toJson(map));
			response.flushBuffer();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 改变list变成图片管理端分页
	 * 
	 * @return
	 */
	private Map<String, Object> changeListToMap(List<Map<String, Object>> list,
			Integer pageNo, Integer size, String filePath) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("state", "SUCCESS");
		List<Map<String, Object>> listJieGuo = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map2 : list) {
			if (map2.get("type").equals("File")) {
				list1.add(map2);
			}
		}

		for (int i = (pageNo - 1) * size; i < list1.size() && i < pageNo * size; i++) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("url", filePath + list1.get(i).get("name"));
			listJieGuo.add(map1);
		}
		map.put("total", list1.size());
		map.put("list", listJieGuo);
		return map;
	}
}
