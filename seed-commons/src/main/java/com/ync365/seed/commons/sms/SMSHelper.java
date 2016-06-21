package com.ync365.seed.commons.sms;

import java.net.MalformedURLException;
import java.net.URL;

import javax.management.RuntimeErrorException;
import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ync365.seed.utils.MD5Utils;

@Component
public class SMSHelper {
    @Value("${sms_sn}")
    private String sms_sn;
    @Value("${sms_pwd}")
    private String sms_pwd;
    @Value("${sms_url_wsdl}")
    private String sms_url_wsdl;

    public String sendSms(String mobile, String content) {
        Service service;
        String rs = null;
        try {
            service = new WebService(new URL(sms_url_wsdl));
            WebServiceSoap soap = service.getPort(WebServiceSoap.class);
            rs = soap.mt(sms_sn, MD5Utils.getMD5Str(sms_sn+sms_pwd).toUpperCase(), mobile, content,"","","");
        } catch (MalformedURLException e) {
            throw new RuntimeException("SMSHelper senSms 错误", e);
        }
        return rs;
    }
}
