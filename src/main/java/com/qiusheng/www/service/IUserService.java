package com.qiusheng.www.service;  


import com.qiusheng.www.common.Result;
import com.qiusheng.www.entity.User;
  
public interface IUserService {  
    User getUserById(int userId);
    Result insertUser(User user);
}  