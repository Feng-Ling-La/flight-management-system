package com.hxh.web.admin;

import com.hxh.bean.User;
import com.hxh.mapper.UserMapper;
import com.hxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    private UserService userService;

    /**
     * 管理员点击 用户信息列表 来到该方法，查询出所有用户，去user_list页面展示
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String UserList(Model model){
        model.addAttribute("user_list",userService.findAll());
        return "admin/user_list";
    }



    /**
     * 删除用户
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model){
        int i = userService.deleteUserById(id);
        model.addAttribute("user_list",userService.findAll());
        return "admin/user_list";
    }



    //找到序号为id的用户，前往修改该用户信息页面
    @GetMapping("/findUser/{id}")
    public String findUser(@PathVariable("id") Integer id, Model model){
        User user=userService.FindUserById(id);
        model.addAttribute("user",user);
        return "admin/update_user";
    }
    //修改id所指用户信息
    @PostMapping("/UpdateInfo")
    public String UpdateInfo(User user,Model model,RedirectAttributes attributes){
        User user1=userService.FindUserById(user.getId());
        if(!user1.getUsername().equals(user.getUsername())&&userService.FindUserByUsername(user.getUsername())!=null ){
            attributes.addFlashAttribute("message","修改失败，用户名已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";
        }
        if(!user1.getRealName().equals(user.getRealName())&&userService.FindUserByRealName(user.getRealName()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该姓名已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";
        }
        if(!user1.getPhone().equals(user.getPhone())&&userService.FindUserByPhone(user.getPhone()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该手机号已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";
        }
        if(!user1.getIdentityNum().equals(user.getIdentityNum())&&userService.FindUserByIdentityNum(user.getIdentityNum())!=null ){
            attributes.addFlashAttribute("message","修改失败，该身份证号已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";
        }
        if(!user1.getEmail().equals(user.getEmail())&&userService.FindUserByEmail(user.getEmail()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该邮箱已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";
        }
        userService.updateUser(user);
        model.addAttribute("user_list",userService.findAll());
        return "admin/user_list";
    }



    //前往查找用户页面find_user
    @GetMapping("/find")
    public String findUser(Model model){
        model.addAttribute("user_list",userService.findAll());
        return "admin/find_user";
    }
    //管理员通过用户名查找用户
    @PostMapping("/findUserByUsername")
    public String findUserByUsername(String username,Model model){
        model.addAttribute("user_list",userService.FindUserByUsername(username));
        return "admin/find_user";
    }
    //管理员通过身份证号查找用户
    @PostMapping("/findUserByIdentityNum")
    public String findUserByIdentityNum(String identityNum,Model model){
        model.addAttribute("user_list",userService.FindUserByIdentityNum(identityNum));
        return "admin/find_user";
    }



    //管理员添加用户，前往添加用户页面
    @GetMapping("/user")
    public String user(){
        return "admin/user_add";
    }
    //添加用户
    @PostMapping("/AddUser")
    public String AddUser(String username, String password, String realName,
                          String identityNum, String Phone, String email, Model model, RedirectAttributes attributes){
        if(userService.FindUserByUsername(username) !=null ){
            attributes.addFlashAttribute("message","添加失败，用户名已被注册");
            return "redirect:/admin/user";
        }
        if(userService.FindUserByRealName(realName) !=null ){
            attributes.addFlashAttribute("message","添加失败，该姓名已被注册");
            return "redirect:/admin/user";
        }
        if(userService.FindUserByPhone(Phone) !=null ){
            attributes.addFlashAttribute("message","添加失败，该手机号码已被注册");
            return "redirect:/admin/user";
        }
        if(userService.FindUserByIdentityNum(identityNum)!=null ){
            attributes.addFlashAttribute("message","添加失败，该身份证号码已被注册");
            return "redirect:/admin/user";
        }
        if(userService.FindUserByEmail(email) !=null ){
            attributes.addFlashAttribute("message","添加失败，该邮箱已被注册");
            return "redirect:/admin/user";
        }
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRealName(realName);
        user.setIdentityNum(identityNum);
        user.setPhone(Phone);
        user.setEmail(email);

        userService.saveUser(user);
        model.addAttribute("user_list",userService.findAll());
        return "admin/user_list";
    }
}
