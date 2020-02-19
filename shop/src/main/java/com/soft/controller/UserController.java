package com.soft.controller;


import com.soft.pojo.User;
import com.soft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/user/userlist")
    public String userlist(Model model){
        List<User> users = userService.selectUser();
        model.addAttribute("users",users);
        return "/user/userlist";
    }

}
