package com.hxh.web;

import com.hxh.bean.Flight;
import com.hxh.bean.User;
import com.hxh.service.FlightService;
import com.hxh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private UserService userService;

    /**
     * 用户或管理员访问 http://localhost:8080/ 都会来到该方法，处理结果是跳转带 首页
     * @param model
     * @return
     */
    @GetMapping("/")
    public String loginPage(Model model){
        model.addAttribute("flightList",flightService.findAll());
        return "default/index";
    }


    //通过出发城市、到达城市、出发时间查找航班
    @PostMapping("/findFlight")
    public String findFlight(String f_start, String f_end, String f_dt, Model model){
        if (f_dt.equals("")&&f_start.equals("")&&f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_startAndF_end(f_start,f_end));

        }else if(!f_dt.equals("")&&f_start.equals("")&&f_end.equals("")) {
            model.addAttribute("flightList", flightService.findFlightsByF_dt(f_dt));
        }else if(f_dt.equals("")&&!f_start.equals("")&&f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_start(f_start));
        }else if(f_dt.equals("")&&f_start.equals("")&&!f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_end(f_end));
        }else if(!f_dt.equals("")&&!f_start.equals("")&&f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_startAndF_dt(f_start,f_dt));
        }else if(!f_dt.equals("")&&f_start.equals("")&&!f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_endAndF_dt(f_end,f_dt));
        }else if(f_dt.equals("")&&!f_start.equals("")&&!f_end.equals("")) {
            model.addAttribute("flightList", flightService.findByF_startAndF_end(f_start,f_end));
        }else{
            model.addAttribute("flightList", flightService.findByF_startAndF_endAndF_dt(f_start,f_end,f_dt));
        }
            return "default/index";
    }

    /**
     * 处理买票，跳转到买票页面
     * @param id
     * @param model
     * @return
     */

    @GetMapping("/buy/{id}")
    public String buyId(@PathVariable("id") Integer id, Model model, HttpSession session, RedirectAttributes attributes){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        User user=userService.FindUserByUsername(username);
        model.addAttribute("user",user);
        Flight flight = flightService.findAllById(id);
        model.addAttribute("flight",flight);
        return "default/showFlight";
    }


    //查看机票详细信息
    @GetMapping("/view/{id}")
    public String viewId(@PathVariable("id") Integer id, Model model, HttpSession session, RedirectAttributes attributes){
        Flight flight = flightService.findAllById(id);
        model.addAttribute("flight",flight);
        return "default/detailFlight";
    }
}

