package com.yll.bookmanager.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.yll.bookmanager.entity.User;
import com.yll.bookmanager.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.yll.bookmanager.util.JwtUtils;

//完成登录校验规则的设定
@Slf4j
@Component
public class AccountRealm extends AuthorizingRealm {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;

    //判断是否是jwt的实例
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    //校验信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获得token
        JwtToken jwt = (JwtToken) token;
        log.info("jwt----------------->{}", jwt);
        String userId = jwtUtils.getClaimByToken((String) jwt.getPrincipal()).getSubject();
        //获得用户名
        User user = userService.getById(Long.parseLong(userId));
        //如果用户名为空
        if(user == null) {
            throw new UnknownAccountException("账户不存在！");
        }
        //如果账户被锁定了
        if(user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定！");
        }
        //讲需要返回的必要信息返回
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        log.info("profile----------------->{}", profile.toString());
        //返回token
        return new SimpleAuthenticationInfo(profile, jwt.getCredentials(), getName());
    }
}

