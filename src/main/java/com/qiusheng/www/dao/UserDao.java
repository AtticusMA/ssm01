package com.qiusheng.www.dao;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.qiusheng.www.entity.User;

@Repository
public interface UserDao {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByName(String username);
}