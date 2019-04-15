package com.qiusheng.www.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Iterator;

public class CustomAccessDecisionManager  implements AccessDecisionManager {
    /**
     * 这里面的参数是如何得到的呢，母鸡
     * @param authentication 用户的认证
     * @param o 资源
     * @param collection 资源的需要的权限集合
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        if(null ==collection){
            return;
        }
        Iterator<ConfigAttribute> iterator=collection.iterator();
        if(iterator.hasNext()){
            ConfigAttribute configAttribute=iterator.next();
            String needPermission =configAttribute.getAttribute();
            System.out.println("访问"+o.toString()+"需要的权限是："+needPermission);
            Collection<? extends GrantedAuthority> authorities=authentication.getAuthorities();
            for(GrantedAuthority grantedAuthority:authorities){
                if(needPermission.equals(grantedAuthority.getAuthority())){
                    return;
                }
            }
        }
        throw new AccessDeniedException("没有访问权限");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
