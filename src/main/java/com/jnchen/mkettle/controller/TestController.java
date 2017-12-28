package com.jnchen.mkettle.controller;

import com.jnchen.mkettle.domain.User;
import com.jnchen.mkettle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caojingchen on 2017/12/26.
 */
@RestController
@RequestMapping(value = "view")
public class TestController {

    @Autowired
    private UserService userService;
    @RequestMapping(value = "index")
    public String index(){
        return "Hello World";
    }


    @RequestMapping(value = "getUser")
    public User getUser(){
        return userService.getUser("d22122927f345e74bdc0103900ab2e4f");
    }
}
