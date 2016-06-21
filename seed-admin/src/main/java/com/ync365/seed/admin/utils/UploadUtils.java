package com.ync365.seed.admin.utils;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.ync365.seed.utils.ConfigUtils;

/**
 * 文件上传工具类
 * 
 */
public class UploadUtils {

    public static final String BASE_PATH = ConfigUtils.getUploadImgBasePath();
    public static final String GOODS_PATH = ConfigUtils.getUploadImgBasePath() + "goods/";
   
    public static String upload(MultipartFile imageFile, String id,String targetPath) {
        if (imageFile.isEmpty()) {
            return "";
        }
        String filename = imageFile.getOriginalFilename();
        String ext = filename.substring(filename.lastIndexOf("."));

        String nfileName = id + "-" + new Date().getTime() + ext;

        String filepath = targetPath;

        File targetFile = new File(filepath, nfileName);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        } else {
            targetFile.delete();
        }
        try {
            imageFile.transferTo(targetFile);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nfileName;
    }

    /**
     * 删除旧的路径
     * 
     * @param oldThemePath
     */
    public static boolean delete(String path,String targetPath) {
    	path = targetPath + path;
        File file = new File(path);
        boolean flag = false;
        if (file.isFile()) {
            flag = file.delete();
        }
        return flag;
    }
}
