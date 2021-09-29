package com.hxh.web.user;


import com.hxh.bean.User;
import com.hxh.mapper.UserMapper;
import com.hxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserRegisterController {


    @Autowired
    private UserService userService;

    /**
     * 用户在 ”用户登录“ 界面，点击”注册“
     * @return
     */
    @GetMapping("/register")
    public String Register(){
        return "user/register";
    }

    /**
     * 处理用户注册请求
     * @param username
     * @param password
     * @param phone
     * @param email
     * @param attributes
     * @return
*/    @PostMapping("/UserRegister")
    public String UserRegister(String username, String password, String realName, String identityNum, String phone, String email, RedirectAttributes attributes){
        if(userService.FindUserByUsername(username) !=null ){
            attributes.addFlashAttribute("message","用户名已被注册");
            return "redirect:/user/register";
        }
        if(userService.FindUserByRealName(realName) !=null ){
            attributes.addFlashAttribute("message","该姓名已被注册");
            return "redirect:/user/register";
        }
        if(userService.FindUserByPhone(phone) !=null ){
            attributes.addFlashAttribute("message","该手机号码已被注册");
            return "redirect:/user/register";
        }
        if(userService.FindUserByIdentityNum(identityNum)!=null ){
            attributes.addFlashAttribute("message","该身份证号码已被注册");
            return "redirect:/user/register";
        }
        if(userService.FindUserByEmail(email) !=null ){
            attributes.addFlashAttribute("message","该邮箱已被注册");
            return "redirect:/user/register";
        }
        if(username.length()==0){
            attributes.addFlashAttribute("message","用户名不能为空");
            return "redirect:/user/register";
        }
        if(password.length()==0){
            attributes.addFlashAttribute("message","密码不能为空");
            return "redirect:/user/register";
        }
        if(realName.length()==0){
            attributes.addFlashAttribute("message","姓名不能为空");
            return "redirect:/user/register";
        }
        if(identityNum.length()==0) {
            attributes.addFlashAttribute("message", "身份证号码不能为空");
            return "redirect:/user/register";
        }
        if(phone.length()==0){
            attributes.addFlashAttribute("message","手机号码不能为空");
            return "redirect:/user/register";
        }
        if(email.length()==0){
            attributes.addFlashAttribute("message","邮箱不能为空");
            return "redirect:/user/register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setIdentityNum(identityNum);
        user.setPhone(phone);
        user.setEmail(email);
        userService.saveUser(user);
        return "user/login";
    }

}
