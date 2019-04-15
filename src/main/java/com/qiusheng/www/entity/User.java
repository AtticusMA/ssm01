package com.qiusheng.www.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Table;
import java.util.Collection;

/**
* @Description:    java类作用描述
* @Author:         qiusheng
* @Company:
* @department:
* @CreateDate:     2019/3/9 17:26
* @UpdateUser:     qiusheng
* @UpdateDate:     2019/3/9 17:26
* @UpdateRemark:   修改内容
* @Version:        1.0
 *
*/


@Table(name="user_t")
public class User {

    private Long id;

    private String username;

    private String password;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username){
        this.username=username;
    }


    public boolean isAccountNonExpired() {
        return false;
    }


    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
    public User(){}

    public User(Long id,String userName){
        this.id=id;
        this.username = userName;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", age=" + age + "]";
	}
}