package com.qiusheng.www.controller;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;  
import org.springframework.ui.Model;  
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qiusheng.www.common.Result;
import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.IUserService;
  
  
@Controller  
@RequestMapping("/user")  
public class UserController {  
	private static Logger log=LoggerFactory.getLogger(UserController.class);
	 @Autowired
	 private IUserService userService;     
    
    // /user/test?id=1
    @RequestMapping(value="/test",method=RequestMethod.GET)  
    public String test(HttpServletRequest request,Model model){  
        int userId = Integer.parseInt(request.getParameter("id"));  
        System.out.println("userId:"+userId);
        User user=null;
        if (userId==1) {
        	 user = new User();  
        	 user.setAge(11);
        	 user.setId(1);
        	 user.setPassword("123");
        	 user.setUserName("javen");
		}
       
        log.debug(user.toString());
        model.addAttribute("user", user);  
        return "index";  
    }
    @RequestMapping(value="/getRegister",method=RequestMethod.GET)
    public String getRegister(){
    	return "sysuser/register";
    }
    
    
    //@RequestMapping(value="login")
   // public String login(User user){
    	//return "sysuser/login";
    //}
    
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public String register(User user){
    	userService.insertUser(user);
    	return "index";  
    }
    
}   
