package com.jnchen.mkettle.controller;

import com.jnchen.mkettle.domain.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caojingchen on 2017/12/29.
 */
@RestController
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@RequestParam User user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(),user.getPass());
        Subject currentUser = SecurityUtils.getSubject();
        try{
            currentUser.login(token);
        }catch (UnknownAccountException uae){
            logger.debug("未知账户");
            return "未知账户";
        }catch (IncorrectCredentialsException ice){
            logger.debug("密码不正确");
            return "密码不正确";
        }catch (LockedAccountException lae){
            logger.debug("账户已锁定");
            return "账户已锁定";
        }catch (ExcessiveAttemptsException eae){
            logger.debug("用户名或密码错误次数过多");
            return "用户名或密码错误次数过多";
        }catch (AuthenticationException ae){
            logger.debug("用户名密码不正确");
            return "用户名密码不正确";
        }
        if(currentUser.isAuthenticated()) {
            return "true";
        }else {
            token.clear();
            return "false";
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout(){
        SecurityUtils.getSubject().logout();
        return "退出成功";
    }
}
