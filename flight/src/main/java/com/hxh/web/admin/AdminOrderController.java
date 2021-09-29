package com.hxh.web.admin;

import com.hxh.bean.Flight;
import com.hxh.bean.Order;
import com.hxh.bean.User;
import com.hxh.service.FlightService;
import com.hxh.service.OrderService;
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
public class AdminOrderController {

    @Autowired
    UserService userService;

    @Autowired
    private FlightService flightService;

    @Autowired
    private OrderService orderService;

    /**
     * 管理员 点击 订单信息列表，来到该方法进行处理。
     *  调用 orderMapper.findAll() 方法查询出所有订单去 order_list页面展示
     * @param model
     * @return
     */
    @GetMapping("/orders")
    public String OrderList(Model model){
        model.addAttribute("order_list",orderService.findAll());
        return "admin/order_list";
    }


    /**
     * 删除订单：通过订单编号去删除。
     *
     * @param Id
     * @param model
     * @return
     */
    @GetMapping("/deleteOrder/{id}")
    public String deleteOrder(@PathVariable("id") Integer Id, Model model){
        //通过订单编号得到这个订单对象
        Order byUserOrderId = orderService.findByUserOrderId(Id);
        //拿到这个订单的类型：是头等舱还是商务舱或者经济舱
        String f_type = byUserOrderId.getF_type();
        //拿到该订单的航班信息
        /*Flight flight = flightService.findAllByF_num(byUserOrderId.getF_num());
*/
        Flight flight=flightService.findAllById(byUserOrderId.getF_id());
        //对相应的票数加一
        if(f_type.equals("头等舱")){
            flight.setF_t_n(flight.getF_t_n()+1);
        }else if(f_type.equals("商务舱")){
            flight.setF_s_n(flight.getF_s_n()+1);
        }else{
            flight.setF_j_n(flight.getF_j_n()+1);
        }
        flightService.updateByF_type(flight);
        orderService.deleteOrder(Id);
        model.addAttribute("order_list",orderService.findAll());
        return "admin/order_list";
    }




    //通过order_id找到订单order
    @GetMapping("/findOrder/{id}")
    public String findOrder(@PathVariable("id") Integer id, Model model){
        Order order=orderService.findByUserOrderId(id);
        model.addAttribute("order",order);
        return "admin/update_order";
    }
    //修改order_id所指订单信息
    @PostMapping("/updateOrder")
    public String updateOrder(Order order, Model model){
        orderService.updateOrder(order);
        model.addAttribute("order_list",orderService.findAll());
        /*return "redirect:/admin/welcome";*/
        return "admin/order_list";
    }




    //前往查找订单页面find_order
    @GetMapping("/findOrder")
    public String findOrder(Model model){
        model.addAttribute("order_list",orderService.findAll());
        return "admin/find_order";
    }
    //管理员通过用户名查找订单
    @PostMapping("/findOrdersByUsername")
    public String findOrdersByUsername(String username,Model model){
        model.addAttribute("order_list",orderService.findAllByUsername(username));
        return "admin/find_order";
    }
    //管理员通过身份证号查找订单
    @PostMapping("/findOrdersByF_num")
    public String findOrdersByF_num(String f_num,Model model){
        model.addAttribute("order_list",orderService.findAllByF_num(f_num));
        return "admin/find_order";
    }




    //前往添加订单页面
    @GetMapping("/order")
    public String Order(){
        return "admin/order_add";
    }
    //管理员添加订单
    @PostMapping("/AddOrder")
    public String AddOrder(String username, String f_id, String f_type, Model model, RedirectAttributes attributes){
        /**
         * 以下代码的作用是，将前端传来的参数转为Integer类型
         *
         * 因为前端页面传参只能传递 String类型的数据，而Flight对象中的属性却Integer类型，所以需要转类型
         */
        Integer f_id_t = Integer.valueOf(f_id);
        /*Integer id_t = Integer.valueOf(id);
        Integer f_price_t = Integer.valueOf(f_price);*/
        if(userService.FindUserByUsername(username) ==null ){
            attributes.addFlashAttribute("message","添加失败，用户名不存在");
            return "redirect:/admin/order";
        }
        if(flightService.findAllById(f_id_t)==null ){
            attributes.addFlashAttribute("message","添加失败，航班不存在");
            return "redirect:/admin/order";
        }
        User user=userService.FindUserByUsername(username);
        Flight flight=flightService.findAllById(f_id_t);
        if(f_type.equals("头等舱")&&flight.getF_t_n()<=0||f_type.equals("商务舱")&&flight.getF_s_n()<=0||f_type.equals("经济舱")&&flight.getF_j_n()<=0)
            return "admin/failToAddOrder";
        //创建一个  Order 对象，并给
        Order order=new Order();
        order.setId(user.getId());
        order.setUsername(username);
        order.setF_id(f_id_t);
        order.setF_num(flight.getF_num());
        order.setF_type(f_type);
        if (f_type.equals("头等舱"))
            order.setF_price(flight.getF_t());
        else if(f_type.equals("商务舱"))
            order.setF_price(flight.getF_s());
        else
            order.setF_price(flight.getF_j());
        //订单时间默认为当前时间
       /* order.setO_date(o_date);*/
        order.setPhone(user.getPhone());
        order.setUserID(user.getIdentityNum());

        //保存 订单信息 将管理员填写的订单信息，写入到数据库中
        if(orderService.saveOrder(order)!=0) {
            //将此航班的对应舱位的剩余票数减一
            // tips：因为integer类型数字大于127，不能直接使用“==”比较，
            if(f_type.equals("经济舱"))
                flight.setF_j_n(flight.getF_j_n()-1);
            else if(f_type.equals("商务舱"))
                flight.setF_s_n(flight.getF_s_n()-1);
            else
                flight.setF_t_n(flight.getF_t_n()-1);

            flightService.updateByF_type(flight);
            model.addAttribute("order_list", orderService.findAll());
            return "admin/order_list";
        }
        else {
            return "redirect:/";
        }
    }

}
