package com.jnchen.mkettle.utils.security;

import com.jnchen.mkettle.domain.User;
import com.jnchen.mkettle.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by caojingchen on 2017/12/28.
 */
public class UserRealm extends AuthorizingRealm{
    @Resource
    private UserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        User user= (User) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String)token.getPrincipal();
        User user = userService.selectByUsername(username);
        if(user==null) throw new UnknownAccountException();
        if (0==user.getEnable()) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user.getName(),
                user.getPass(),
                this.getName()
        );
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }
}
