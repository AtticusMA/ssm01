package com.qiusheng.www.security;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//可以通过此类自定义自己的用户名密码过滤器，还有一个问题就是密码加密的是不是在这里解决
public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private  final String username="j_username";

    private  final String password="j_password";

    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        if(!request.getMethod().equals("POST")){
            throw new AuthenticationServiceException("Authentication method not supported:"+request.getMethod());
        }
        String username =this.obtainUsername(request);
        String password = this.obtainPassword(request);
        username = username.trim();
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(username,password);
        return this.getAuthenticationManager().authenticate(usernamePasswordAuthenticationToken);
    }

    public String obtainUsername(HttpServletRequest request){
        String j_username = request.getParameter(username);
        return null==j_username ?"":j_username.toString();
    }
    public String obtainPassword(HttpServletRequest request){
        String j_password= request.getParameter(password);
        return null == j_password ? "" :j_password;
    }
}
