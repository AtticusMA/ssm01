package com.qiusheng.www.service;  


import com.qiusheng.www.common.Result;
import com.qiusheng.www.entity.User;
  
public interface IUserService {  
    public User getUserById(int userId);  
    public Result insertUser(User user);
}  