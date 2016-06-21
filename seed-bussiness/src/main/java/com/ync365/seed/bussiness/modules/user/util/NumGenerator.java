package com.ync365.seed.bussiness.modules.user.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ync365.seed.commons.redis.JedisTemplate;
import com.ync365.seed.commons.redis.JedisTemplate.JedisActionNoResult;
import com.ync365.seed.utils.StringUtils;

import redis.clients.jedis.Jedis;

/**
 * 生成各种编号
 * 
 * @author duan.zhao.qian
 * @date 2015年9月9日 下午5:19:10
 */
@Service
public class NumGenerator {

	@Autowired
	private JedisTemplate temp;

	@PostConstruct
	public void init() {
		template = temp;
	}

	private static Integer expireSecond =60 * 60 * 24 * 2;
	private static JedisTemplate template;

	/**
	 * 获取用户编号
	 * 
	 * @return
	 */
	public static  String getUserNum() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String datePrefix = "11" + format.format(Calendar.getInstance().getTime());
		final String idkey = "user_num_generator_identity_" + datePrefix;
		long n = template.incr(idkey);
		template.expire(idkey, expireSecond);
		String result = datePrefix + StringUtils.leftPad(String.valueOf(n), 8, "0");
		return result;
	}

	/**
	 * 获取POP商家编号
	 * 
	 * @return
	 */
	public static String getPoPNum() {
		SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
		String datePrefix = "12" + format.format(Calendar.getInstance().getTime());
		final String idkey = "user_num_generator_identity_" + datePrefix;
		long n = template.incr(idkey);
		template.expire(idkey, expireSecond);
		String result = datePrefix + StringUtils.leftPad(String.valueOf(n), 8, "0");
		return result;
	}

    /**
     * 获取SS编号
     * 
     * @return
     */
    public static String getSSNum() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String datePrefix = "13" + format.format(Calendar.getInstance().getTime());
        final String idkey = "user_num_generator_identity_" + datePrefix;
        long n = template.incr(idkey);
        template.expire(idkey, expireSecond);
        String result = datePrefix + StringUtils.leftPad(String.valueOf(n), 8, "0");
        return result;
    }
	/**
	 * 获取群组编号
	 * 
	 * @return
	 */
	public static String getGroupNum() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 获取角色编号
	 * 
	 * @return
	 */
	public static String getRoleNum() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	/**
	 * 获取权限编号
	 * 
	 * @return
	 */
	public static String getModuleNum() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

    public static String getAdminNum() {
        SimpleDateFormat format = new SimpleDateFormat("yyMMdd");
        String datePrefix = "15" + format.format(Calendar.getInstance().getTime());
        final String idkey = "user_num_generator_identity_" + datePrefix;
        long n = template.incr(idkey);
        template.expire(idkey, expireSecond);
        String result = datePrefix + StringUtils.leftPad(String.valueOf(n), 8, "0");
        return result;
    }
}
