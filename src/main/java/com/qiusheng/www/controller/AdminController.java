package com.qiusheng.www.controller;

import com.qiusheng.www.common.BaseController;
import com.qiusheng.www.entity.User;
import com.qiusheng.www.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/admin")
@Controller
public class AdminController extends BaseController {

    @Autowired
    private IUserService userService;

    public User get(@RequestParam(required = false)String id){
        User entity = null;
        if(StringUtils.isNotBlank(id)){
            entity=userService.getUserById(Long.parseLong(id));
        }else{
            entity = new User();
        }
        return entity;
    }
    @RequestMapping(value={"/list",""})
    public String getList(Model model){
        User user = getCurrentUser();
        model.addAttribute("admin",user);
        return "/admin/admin";
    }
}
