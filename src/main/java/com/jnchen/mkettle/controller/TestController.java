package com.jnchen.mkettle.controller;

import com.jnchen.mkettle.domain.User;
import com.jnchen.mkettle.repository.UserRepository;
import com.jnchen.mkettle.service.UserService;
import com.jnchen.mkettle.utils.schedule.ScheduleUtil;
import com.jnchen.mkettle.utils.security.MD5Util;
import com.jnchen.mkettle.utils.task.TestTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by caojingchen on 2017/12/26.
 */
@RestController
@RequestMapping(value = "view")
public class TestController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "index")
    public String index(){
        return "Hello World";
    }


    @RequestMapping(value = "getUser")
    public User getUser(){
        return userService.getUser(1L);
    }

    @RequestMapping(value = "allUser")
    public List<User> allUser(){
        return userRepository.findAll();
    }

    @RequestMapping(value = "add")
    public String add(){
        User user = new User();
        user.setName("test");
        user.setPass(MD5Util.encode("123456"));
        user.setEnable(1);
        user.setIsAdmin(0);
        userRepository.save(user);
        return "success";
    }

    @RequestMapping(value = "start")
    public void start(String taskname){
        ScheduleUtil.startTask(taskname,new TestTask(),"0/10 * * * * *");
    }

    @RequestMapping(value = "change")
    public void change(String taskname){
        ScheduleUtil.changeTask(taskname,new TestTask(),"0 0/1 * * * *");
    }

    @RequestMapping(value = "stop")
    public void stop(String taskname){
        ScheduleUtil.stopTask(taskname);
    }
}
