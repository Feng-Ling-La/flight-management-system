package com.hxh.web.user;

import com.hxh.bean.User;
import com.hxh.mapper.UserMapper;
import com.hxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserLoginController {

    @Autowired
    private UserService userService;

    /**
     * 用户点击 “用户登录” 来到该方法，即：用户登录界面
     * @return
     */
    @GetMapping
    public String Login(){
        return "user/login";
    }

    /**
     * 处理登录登录请求，用户输入用户名密码
     * @param username 前端提交的用户名
     * @param password 密码
     * @param session
     * @param attributes
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        //检查数据库中有没有该用户
        User user = userService.FindUserByUsername(username);
        if(user == null){
            attributes.addFlashAttribute("message","用户不存在");
            return "redirect:/user";
        }
        else if(user.getPassword().equals(password))
        {
            //记录登录状态
            session.setAttribute("username",username);
            return "redirect:/";
        }else{
            attributes.addFlashAttribute("message","密码错误");
            return "redirect:/user";
        }
    }


    /**
     * 用户注销登录
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("username");//移除保存在session中的username属性
        return "redirect:/";
    }
}
