package com.qiusheng.www.common;

import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;


@Controller
public abstract class BaseController {


    @Autowired
    private UserServiceImpl userService;
    //protected Logger logger = LoggerFactory.getLogger(BaseController.class);
    //protected Logger logger = LoggerFactory.getLogger(getClass());

    //@Value("${adminPath}")
    protected String adminPath;

    public User getCurrentUser(){
        Authentication a=SecurityContextHolder.getContext().getAuthentication();
        User user=userService.getUserByName(a.getName());
         return user;
    }

}
