package com.qiusheng.www.service.Impl;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiusheng.www.common.Result;
import com.qiusheng.www.dao.UserDao;
import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.IUserService;  

  
  
@Service
public class UserServiceImpl implements IUserService {  
    @Autowired
    private UserDao userDao;  
    
    public User getUserById(int userId) {  
        // TODO Auto-generated method stub  
        return this.userDao.selectByPrimaryKey(userId);  
    }

	@Override
	public Result insertUser(User user) {
		// TODO Auto-generated method stub
		int code=1;
		String message="保存失败";
		
		 if(1==userDao.insert(user)){
			 code=0;
			 message="成功保存";
		 }
		 return new Result(code,message,user);
	}  
  
}  
