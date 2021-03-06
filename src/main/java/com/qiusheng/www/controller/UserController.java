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
        	 user.setUsername("javen");
		}
        model.addAttribute("user", user);  
        return "index";  
    }
    @RequestMapping(value="/getRegister",method=RequestMethod.GET)
    public String getRegister(){
    	return "sysuser/register";
    }


    @RequestMapping(value="/getLogin",method=RequestMethod.GET)
    public String getlogin(){
        return "login";
    }

    
    @RequestMapping(value="/login")
    public String loginSuccess(User user,Model model){
        user=getCurrentUser();
        model.addAttribute("user",user);
    	return "index";
    }


    @RequestMapping(value="loginOutSuccess")
    public String loginOutSuccess(User user,Model model){
        user=getCurrentUser();
        model.addAttribute("user",user);
        return "login";
    }

    @RequestMapping(value="/loginError")
    public String loginError(User user){
        return "loginerror";
    }


    //这个是如何将user内容直接带入到下一个页面的呢
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(User user){
        user.setAge(1);
        user.setId(IdUtil.generateId());
    	userService.insertUser(user);
    	return "index";  
    }

    
}   
