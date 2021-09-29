package com.hxh.web.admin;


import com.hxh.bean.Admin;
import com.hxh.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminLoginController {

    @Autowired
    private AdminService adminService;


    /**
     * @GetMapping 后不写括号 也不写值。表示处理的请求是 "/"
     *
     * 该方法的作用是：管理员点击 “管理员登录” 按钮。跳转到 管理员登录页面，即：管理员输用户名密码的地方
     *  即：resources-->templates-->admin--->login.html
     * @return
     */
    @GetMapping
    public String loginPage(){
        return "admin/login";
    }

    /**
     * 管理员填写完 用户名 密码 之后，点击 "登录" 就来到该请求。
     *
     * 结果：登录成功 则跳转到管理员后台界面 resources-->templates-->admin--->frame.html
     *  登录失败则发起重定向，即来到管理员登录界面，重新输入用户名密码
     * @param adminName 管理员输入的用户名
     * @param password 密码
     * @param session 用来判断当前管理员的登录状态的
     * @param attributes 用来做提示信息的。即：提示：“管理员不存在” 或 "用户名或密码不正确"
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam String adminName,
                        @RequestParam String password,
                        HttpSession session, RedirectAttributes attributes){
        Admin admin = adminService.FindAdminByName(adminName);
        if(admin == null){
            attributes.addFlashAttribute("message","管理员不存在");
            return "redirect:/admin";
        }else if(admin.getPassword().equals(password)){
            //使用session记录登录状态
            session.setAttribute("loginAdmin",adminName);
            return "admin/frame";
        }else{
            attributes.addFlashAttribute("message","密码错误");
            return "redirect:/admin";
        }
    }


    /**
     * 管理员的注销，注销完成之后来到：首页：即：resources-->templates-->default-->index.html
     * @param session
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginAdmin");
        return "redirect:/";
    }

    /**
     * 管理员的欢迎界面
     * @return
     */
    @GetMapping("/welcome")
    public String welcome(){
        return "admin/welcome";
    }

}
