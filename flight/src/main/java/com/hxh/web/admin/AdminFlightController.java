package com.hxh.web.admin;

import com.hxh.bean.Flight;
import com.hxh.mapper.FlightMapper;
import com.hxh.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * controller 类，就是控制页面跳转的类，可以理解为：中央处理机，它会接受前端发起的请求，并选择合适的
 *  方法或页面去执行或显示。
 *
 *  @Controller  该注解用于注解类，表示该类是一个Controller类
 * @RequestMapping("/admin") 该注解可以注解类，也可以注解方法
 *      1. 如果注解在类上，表示该中的  @GetMapping或 @PostMapping 后的括号中会加上一个前缀。
 *          例：方法 Flight() 上加了注解 @GetMapping("/flight")，它真正能处理的请求是："/admin/flight"
*       2. 如果注解在方法上，表示该方法能处理前端发起的哪些请求。
 *          @RequestMapping 又可细分为
 *              1）、@GetMapping：处理以Get方式发起的请求，一般请求下，前端的 <a></a> 即：超链接。发起的请求类型是Get类型
 *              2）、@PostMapping：处理以Post方式发起的请求，一般情况下，form表单如果传输的内容比较敏感，就使用Post 方式
 *              发起请求。
 *         @RequestMapping 即可处理 Get方式的请求，也可处理Post方式的请求
 */
@Controller
@RequestMapping("/admin")
public class AdminFlightController {

    /**
     * 从Spring容器中得到 flightMapper 对象，可以理解为，创建一个flightMapper对象，供我们后面使用
     */
    @Autowired
    private FlightService flightService;




    /**
     * 处理前端发起的 /admin/flight 请求。
     *  处理结果是：跳转到 admin/flight_add。
     *      即：跳转到 项目左侧 resources--->templates --> web --> admin --> flight_add.html
     *    也就是展示 flight_add.html页面
     *
     *  作用：管理员 点击 “添加航班信息” 就会发起 /admin/flight 请求，就会来到该方法，
     *      该方法就会去展示 添加航班信息 的表格，用来给管理员填写要添加的航班信息
     * @return
     */
    @GetMapping("/flight")
    public String Flight(){
        return "admin/flight_add";
    }
    /**
     * 处理前端发起的  /admin/AddFlight 请求。
     * 作用：
     *      管理员在填写完 “添加航班信息” 的表单后，点击 “提交” 会发起 /admin/AddFlight，就会来到该方法
     *    该方法的处理结果是将管理员要 添加的航班信息 保存到数据库的表中。
     *      保存的时候调用的方法是：flightMapper.saveFlight(flight);
     *
     *  该方法处理完成之后，来到 “航班信息列表” 页面 也就是：resources-->templates-->admin-->flight_list.html
     *
     * 以下参数都是 管理员 填写的航班信息表单
     * @param Flight_num  航班名
     * @param Flight_start 起点
     * @param Flight_end 终点
     * @param Flight_dt 起飞时间
     * @param Flight_at 到达时间
     * @param Flight_t 头等舱价格
     * @param Flight_s 商务舱价格
     * @param Flight_j 经济舱价格
     * @param Flight_t_n 头等舱数量
     * @param Flight_s_n 商务舱数量
     * @param Flight_j_n 经济舱数量
     * @param model 用来前端页面传递参数的 容器，底层就是一个 Map<String,Object>
     * @return
     */
    @PostMapping("/AddFlight")
    public String AddFlight(String Flight_num, String Flight_start,
                            String Flight_end, String Flight_dt, String Flight_at, String Flight_t,
                            String Flight_s, String Flight_j, String Flight_t_n, String Flight_s_n, String Flight_j_n, Model model){
        /**
         * 以下六行代码的作用是，将前端传来的参数转为Integer类型
         *
         * 因为前端页面传参只能传递 String类型的数据，而Flight对象中的属性却Integer类型，所以需要转类型
         */
        Integer f_t_n = Integer.valueOf(Flight_t_n);
        Integer f_t = Integer.valueOf(Flight_t);
        Integer f_s_n = Integer.valueOf(Flight_s_n);
        Integer f_s = Integer.valueOf(Flight_s);
        Integer f_j_n = Integer.valueOf(Flight_j_n);
        Integer f_j = Integer.valueOf(Flight_j);

        //创建一个  Flight 对象，并给
        Flight flight = new Flight();
        flight.setF_num(Flight_num);
        flight.setF_start(Flight_start);
        flight.setF_end(Flight_end);
        flight.setF_dt(Flight_dt);
        flight.setF_at(Flight_at);
        flight.setF_t(f_t);
        flight.setF_s(f_s);
        flight.setF_j(f_j);
        flight.setF_t_n(f_t_n);
        flight.setF_s_n(f_s_n);
        flight.setF_j_n(f_j_n);

        //保存 航班信息 将管理员填写的航班信息，写入到数据库中
        flightService.saveFlight(flight);
        model.addAttribute("flight_list",flightService.findAll());
        return "admin/flight_list";
    }




    /**
     * 管理员点击 “航班信息列表" 发起：/admin/flights 请求，来到该方法
     * 作用：
     *      去数据库中查询出所有航班信息（使用flightMapper.findAll()方法查询），放进Model中去页面展示。
     * 结果：到航班信息列表页面展示当前所有的航班信息，
     *      即：resources-->templates-->admin--->flight_list.html
     * @param model
     * @return
     */
    @GetMapping("/flights")
    public String FlightList(Model model){
        model.addAttribute("flight_list",flightService.findAll());
        return "admin/flight_list";
    }

    /**
     * 删除航班信息，调用 flightMapper.deleteFlight(Id)  方法去根据id删除航班信息
     *
     * 删除完之后，又去查询出当前所有的航班信息，flightMapper.findAll()
     *
     * 结果：到航班信息列表页面展示当前所有的航班信息，
     *      即：resources-->templates-->admin--->flight_list.html
     * @param Id 要删除的航班id
     * @param model
     * @return
     */
    @GetMapping("/deleteFlight/{id}")
    public String deleteFlight(@PathVariable("id") Integer Id, Model model){
        flightService.deleteFlight(Id);
        model.addAttribute("flight_list",flightService.findAll());
        return "admin/flight_list";
    }



    //通过f_id找到航班
    @GetMapping("/findFlight/{id}")
    public String findFlight(@PathVariable("id") Integer id, Model model){
        Flight flight=flightService.findAllById(id);
        model.addAttribute("flight",flight);
        return "admin/update_flight";
    }
    //修改f_id所指航班信息
    @PostMapping("/updateFlight")
    public String updateFlight(Flight flight,Model model){
        flightService.updateFlight(flight);
        model.addAttribute("flight_list",flightService.findAll());
        /*return "redirect:/admin/welcome";*/
        return "admin/flight_list";
    }



    //前往查找航班页面find_flight
    @GetMapping("/findFlight")
    public String findFlight(Model model){
        model.addAttribute("flightList",flightService.findAll());
        return "admin/find_flight";
    }
    //管理员通过航班号查找航班
    @PostMapping("/findAllByF_num")
    public String findAllByF_num(String f_num,Model model){
        model.addAttribute("flightList",flightService.findAllByF_num(f_num));
        return "admin/find_flight";
    }
    //管理员通过出发城市和到达城市查找航班
    /*@PostMapping("/findSomeFlight")
    public String SomeFlight(String f_start,String f_end,Model model){
        model.addAttribute("flight_List", flightService.findSomeFlight(f_start,f_end));
        return "admin/find_flight";
    }*/
    //管理员通过出发城市、到达城市、出发时间查找航班
    @PostMapping("/findFlight")
    /*public String SomeFlight(String StartFly,String EndFly,Model model){
        if(StartFly.equals("")&&EndFly.equals("")){
            model.addAttribute("flightList",flightService.findSomeFlight(StartFly,EndFly));
        }else if(!StartFly.equals("")&&EndFly.equals("")) {
            model.addAttribute("flightList", flightService.findSomeFlightOne(StartFly));
        }else if(StartFly.equals("")&&!EndFly.equals("")) {
            model.addAttribute("flightList", flightService.findSomeFlightTwo(EndFly));
        }else{
            model.addAttribute("flightList", flightService.findSomeFlight(StartFly,EndFly));
        }
        return "admin/find_flight";
    }*/

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
        return "admin/find_flight";
    }



}