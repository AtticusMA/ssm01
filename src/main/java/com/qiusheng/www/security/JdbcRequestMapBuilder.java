package com.qiusheng.www.security;

import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

public class JdbcRequestMapBuilder extends JdbcDaoSupport {

    /**
     * 写入sql查询语句
     */
    private String resourceQuery="";

    public String getResourceQuery() {
        return resourceQuery;
    }

    public void setResourceQuery(String resourceQuery) {
        this.resourceQuery = resourceQuery;
    }

    /**
     * 执行sql,获取List<resource>
     * @return
     */
    public List<Resource> findResourceData(){
        ResourceMapping resourceMapping = new ResourceMapping(getDataSource(),getResourceQuery());
        return resourceMapping.execute();
    }

    /**
     * 包装url，生成security识别的RequestMatcher
     * @param url
     * @return
     */
    public RequestMatcher getRequestMatcher(String url){
        return new AntPathRequestMatcher(url);
    }

    /**
     * 生成最终spring security所能识别的格式
     * @return
     */
    public LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>> buildRequestMap(){
        LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>> linkedHashMap = new LinkedHashMap<>();
        List<Resource> resources = this.findResourceData();
        for(Resource resource:resources){
            RequestMatcher requestMatcher=getRequestMatcher(resource.getUrl());
            List<ConfigAttribute> configAttributes=new ArrayList<>();
            configAttributes.add(new SecurityConfig(resource.getRole()));
            linkedHashMap.put(requestMatcher,configAttributes);
        }
        return  linkedHashMap;
    }
}
