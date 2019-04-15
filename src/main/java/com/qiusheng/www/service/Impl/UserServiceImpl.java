package com.qiusheng.www.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qiusheng.www.common.Result;
import com.qiusheng.www.dao.UserDao;
import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.IUserService;  

  
  
@Service
public class UserServiceImpl implements IUserService {


	@Autowired
	private  UserDao userDao;


	@Override
	public User getUserById(Long userId) {
		return this.userDao.selectByPrimaryKey(userId);
	}

	public Result insertUser(User user) {
		int code=1;
		String message="保存失败";

		if(1==userDao.insert(user)){
			code=0;
			message="成功保存";
		}
		return new Result(code,message,user);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getUserByName(username);
	}

}
