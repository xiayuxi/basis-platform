package com.ync365.seed.admin.controller.base;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public class BaseController {

	/**
	 * 错误视图
	 */
	protected static final String ERROR_VIEW = "/common/error";
	
    /**时间自动格式化
     * @author xieang
     * 2015年9月15日
     * @param bin
     */
    @InitBinder
    public void InitBinder(ServletRequestDataBinder bin) {
        bin.registerCustomEditor(Date.class, new CustomDateEditor( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }
}
