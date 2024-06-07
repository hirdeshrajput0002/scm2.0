package com.scm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/user")
public class UserController {
    //user dashboard page
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public String userDashboard(){
        return "user/dashboard";
    }
    // user profile page
    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public String userProfile(){
        return "user/profile";
    }
    //user add contacts page
    //user view contacts
    //user edit contacts
    //user delete contacts
    //user search contacts


}
