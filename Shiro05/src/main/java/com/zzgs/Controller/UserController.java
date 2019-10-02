package com.zzgs.Controller;

import com.zzgs.services.UserServices;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Author:   Tang
 * Date:     2019/10/2 10:37
 * Description:
 */
@Controller
@RequestMapping("/user")
public class UserController {

    /**
     * 如果登陆失败跳转到主页面main.jsp
     * 登陆失败跳转到login.jsp
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public ModelAndView login(@RequestParam("username")String username,
                              @RequestParam("password")String password){

        ModelAndView model = new ModelAndView();
        Subject currentUser = SecurityUtils.getSubject();

        if (!currentUser.isAuthenticated()) {
            //把用户名和密码封装为UsernamePasswordToken对象
            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
            // rememberme
            token.setRememberMe(true);
            try {
                //执行登录
                currentUser.login(token);
            }
            // 若没有指定的账户则shiro会抛出UnknownAccountException异常
            catch (UnknownAccountException uae) {
                System.out.println(uae.getMessage());
                model.addObject("msg",uae.getMessage());
                model.setViewName("/login.jsp");
                return model;
            }
            //若账户存在密码不对则shiro会抛出IncorrectCredentialsException异常
            catch (IncorrectCredentialsException ice) {
                System.out.println(ice.getMessage());
                model.addObject("msg","密码不正确");
                model.setViewName("/login.jsp");
                return model;
            }
            // 用户被锁定的异常LockedAccountException
            catch (LockedAccountException lae) {
                System.out.println(lae.getMessage());
                model.addObject("msg",lae.getMessage());
                model.setViewName("/login.jsp");
                return model;
            }
            // 所有认证异常的父类
            catch (AuthenticationException ae) {
                //unexpected condition?  error?
                return null;
            }
        }
        model.setViewName("redirect:/main.jsp");
        return model;
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping("/logout")
    public String logout(){
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "/login.jsp";
    }
}
