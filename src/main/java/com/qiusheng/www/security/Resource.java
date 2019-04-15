package com.qiusheng.www.security;

public class Resource {
    /**
     * 访问的资源地址
     */
    private String url;

    /**
     * 对应的角色
     */
    private String role;

    public Resource(String url, String role) {
        this.url = url;
        this.role = role;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
