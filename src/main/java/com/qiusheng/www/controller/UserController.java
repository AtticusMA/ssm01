package com.qiusheng.www.controller;
import javax.servlet.http.HttpServletRequest;

import com.qiusheng.www.common.BaseController;
import com.qiusheng.www.common.IdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.IUserService;

import java.util.UUID;


@Controller  
@RequestMapping(value="/user")
public class UserController extends BaseController {

	 @Autowired
	 private IUserService userService;

	 private final static Logger loger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
        	 user = new User();  
        	 user.setAge(11);
        	 user.setId(IdUtil.generateId());
        	 user.setPassword("123");
        	 user.setUserName("javen");
		}
        model.addAttribute("user", user);  
        return "index";  
    }
    @RequestMapping(value="/getRegister",method=RequestMethod.GET)
    public String getRegister(){
    	return "sysuser/register";
    }
    
    
    @RequestMapping(value="login")
    public String login(User user){
    	return "sysuser/login";
    }


    
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(User user){
        user.setAge(1);
        user.setId(IdUtil.generateId());
    	userService.insertUser(user);
    	return "index";  
    }


    
}   
