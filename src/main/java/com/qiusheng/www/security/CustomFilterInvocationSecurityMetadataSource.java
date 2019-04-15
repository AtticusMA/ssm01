package com.qiusheng.www.security;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class CustomFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean {

    /**
     * 用于初始化的Builder，获得requestMap(resource)
     */
    private JdbcRequestMapBuilder builder;

    public JdbcRequestMapBuilder getBuilder() {
        return builder;
    }

    public void setBuilder(JdbcRequestMapBuilder builder) {
        this.builder = builder;
    }

    /**
     * 装载spring security识别的权限信息；
     */
    private Map<RequestMatcher,Collection<ConfigAttribute>> requestMap;

    /**
     * 用于没有匹配的时候返回
     */
    private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE=null;
    @Override
    public void afterPropertiesSet() throws Exception {
        this.requestMap = this.bindRequestMap();
    }

    public void refreshResuorceMap() {
        this.requestMap = this.bindRequestMap();
    }


    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        HttpServletRequest request=((FilterInvocation)o).getRequest();
        Collection<ConfigAttribute> configAttributes = NULL_CONFIG_ATTRIBUTE;
        for(Map.Entry<RequestMatcher,Collection<ConfigAttribute>> entry:requestMap.entrySet()){
            if(entry.getKey().matches(request)){
                configAttributes=entry.getValue();
                break;
            }
        }
        return configAttributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        //为什么使用set，就是因为set不重复，重复的值自动取消
        Set<ConfigAttribute>  configAttributeSet= new HashSet<>();
        for(Map.Entry<RequestMatcher,Collection<ConfigAttribute>> requestMatcherCollectionEntry:requestMap.entrySet()){
            configAttributeSet.addAll(requestMatcherCollectionEntry.getValue());
        }
        System.out.println("所有的权限:"+configAttributeSet.toString());
        return configAttributeSet;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }

    public LinkedHashMap<RequestMatcher,Collection<ConfigAttribute>> bindRequestMap(){
        return builder.buildRequestMap();
    }
}
