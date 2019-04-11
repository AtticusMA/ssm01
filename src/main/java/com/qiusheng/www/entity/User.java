package com.qiusheng.www.entity;


import javax.persistence.Table;

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

    private String userName;

    private String password;

    private Integer age;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassword() {
        return password;
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
        this.userName = userName;
    }

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", password="
				+ password + ", age=" + age + "]";
	}
}