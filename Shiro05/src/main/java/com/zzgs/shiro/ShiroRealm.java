package com.zzgs.shiro;

import com.zzgs.domain.User;
import com.zzgs.services.UserServices;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:58
 * Description:
 */
public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserServices userServices;
    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //得到用户名
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        User user = userServices.findByUserName(username);
        //判断用户是否在数据库中存在
        if (user == null) {
            //如果用户名不存在  抛出UnknownAccountException
            throw new UnknownAccountException("用户名不存在");
        }
        //如果用户名存在 状态处于被锁定 抛出LockedAccountException Status = 0
        if (user.getStatus()==0){
            throw new LockedAccountException("账户被锁定");
        }
        //密码的比较 是shiro内部进行比较
        Object principal = username;
        Object credentials = user.getPassword(); //数据库中查询出的密码
        //盐值加密
        ByteSource credentialsSalt = ByteSource.Util.bytes(username);
        //AuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,super.getName());
        AuthenticationInfo info = new SimpleAuthenticationInfo(principal,credentials,credentialsSalt,super.getName());
        //如果用户名存在但是密码错误 底层会抛出 抛出IncorrectCredentialsException
        return info;
    }

    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //得到用户名信息
        String username = (String) principalCollection.getPrimaryPrincipal();
        //给当前的用户赋予相应的角色
        Set<String> roles = userServices.findRoleByUserName(username);
        if(roles.contains("admin")){
            //管理员是特殊角色
            roles = userServices.findRoles();
        }
        AuthorizationInfo info = new SimpleAuthorizationInfo(roles);
        return info;
    }

    public static void main(String[] args) {
        String username = "mao";
        Object credentials = "1234";
        String hashAlgorithmName = "MD5";
        Object salt = ByteSource.Util.bytes(username);
        int hashIterations = 10;
        Object result = new SimpleHash(hashAlgorithmName,credentials,salt,hashIterations);
        System.out.println(result);
    }
}
