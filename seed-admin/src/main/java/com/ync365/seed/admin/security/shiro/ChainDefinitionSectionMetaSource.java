package com.ync365.seed.admin.security.shiro;

import org.apache.shiro.config.Ini;
import org.apache.shiro.config.Ini.Section;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;

public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section>{  
 
  
    private String filterChainDefinitions;  
  
    /** 
     * 默认premission字符串 
     */  
    public static final String PREMISSION_STRING="perms[\"{0}\"]";  
  
    public Section getObject() throws BeansException {  
  
 
  
        Ini ini = new Ini();  
        //加载默认的url  
        ini.load(filterChainDefinitions);  
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);  
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,  
       
  
        return section;  
    }  
  
    /** 
     * 通过filterChainDefinitions对默认的url过滤定义 
     *  
     * @param filterChainDefinitions 默认的url过滤定义 
     */  
    public void setFilterChainDefinitions(String filterChainDefinitions) {  
        this.filterChainDefinitions = filterChainDefinitions;  
    }  
  
    public Class<?> getObjectType() {  
        return this.getClass();  
    }  
  
    public boolean isSingleton() {  
        return false;  
    }  
  
}
