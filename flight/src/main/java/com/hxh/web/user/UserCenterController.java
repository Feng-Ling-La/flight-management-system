package com.hxh.web.user;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserCenterController {

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private FlightService flightService;


    /**
     * 用户点击 “用户中心” 来到该方法
     * @param attributes
     * @param session
     * @return
     */
    @GetMapping("/UserCenter")
    public String Center(RedirectAttributes attributes, HttpSession session){
        //判断登录状态
        String username = (String) session.getAttribute("username");
        if(username == null){
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }else{
            session.setAttribute("user",userService.FindUserByUsername(username));
            return "user/user_center";
        }

    }

    @GetMapping("/welcome")
    public String welcome(RedirectAttributes attributes,HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message", "您无权限访问，请先登录");
            return "redirect:/user";
        }
        else
            return "user/welcome";
    }
//    @GetMapping("/welcome")
//    public String welcome(){
//        return "user/welcome";
//    }
    /**
     *个人信息
     * @param attributes
     * @param session
     * @return
     */
    @GetMapping("/UserInfo")
    public String Info(RedirectAttributes attributes, HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null){
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }else{
            return "user/user_info";
        }
    }

    @GetMapping("/UpInfo")
    public String Update(RedirectAttributes attributes,HttpSession session){
        String username =(String) session.getAttribute("username");
        if(username == null){
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        else{
            return "user/edit_info";
        }
    }

    @PostMapping("/UpdateInfo")
    public String updateInfo(User user, HttpSession session, RedirectAttributes attributes){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        User user1=userService.FindUserById(user.getId());
        if(!user1.getUsername().equals(user.getUsername())&&userService.FindUserByUsername(user.getUsername())!=null ){
            attributes.addFlashAttribute("message","修改失败，用户名已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            /*attributes.addAttribute("id",user.getId());
            return "redirect:/admin/findUser/{id}";*/
            return "redirect:/user/UpInfo";
        }
        if(!user1.getRealName().equals(user.getRealName())&&userService.FindUserByRealName(user.getRealName()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该姓名已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            return "redirect:/user/UpInfo";
        }
        if(!user1.getPhone().equals(user.getPhone())&&userService.FindUserByPhone(user.getPhone()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该手机号已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            return "redirect:/user/UpInfo";
        }
        if(!user1.getIdentityNum().equals(user.getIdentityNum())&&userService.FindUserByIdentityNum(user.getIdentityNum())!=null ){
            attributes.addFlashAttribute("message","修改失败，该身份证号已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            return "redirect:/user/UpInfo";
        }
        if(!user1.getEmail().equals(user.getEmail())&&userService.FindUserByEmail(user.getEmail()) !=null ){
            attributes.addFlashAttribute("message","修改失败，该邮箱已被注册");
            /*return "admin/update_user";*/
            /*String id=String.valueOf(user.getId());*/
            return "redirect:/user/UpInfo";
        }
        userService.updateUser(user);
        session.setAttribute("username",user.getUsername());
        session.setAttribute("user",user);
        /*return "redirect:/user/UserCenter";*/
        return "redirect:/user/welcome";
    }

    /**
     * 查询自己的订单 票
     * @param session
     * @param model
     * @param attributes
     * @return
     */
    @GetMapping("/getMyTicket")
    public String getMyTicket(HttpSession session, Model model, RedirectAttributes attributes) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        List<Order> list = orderService.findByUserName(username);
        for(int i=0;i<list.size();i++)
        {
            Flight flight = flightService.findAllById(list.get(i).getF_id());
            list.get(i).setF_start(flight.getF_start());
            list.get(i).setF_end(flight.getF_end());
            list.get(i).setF_dt(flight.getF_dt());
            list.get(i).setF_at(flight.getF_at());

        }
        model.addAttribute("userFlightList", list);
        return "user/order_list";
    }

    /**
     * 退票，将库存加一
     * @param order_id 定单id
     * @param attributes
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/refundTicket/{order_id}")
    public String retundTicket(@PathVariable("order_id") Integer order_id, RedirectAttributes attributes, Model model, HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }

        Order order = orderService.findByUserOrderId(order_id);
        String f_type = order.getF_type();
       /* Flight flight = flightService.findAllByF_num(byUserOrderId.getF_num());*/
        Flight flight=flightService.findAllById(order.getF_id());

        if(f_type.equals("头等舱")){
            flight.setF_t_n(flight.getF_t_n()+1);
        }else if(f_type.equals("商务舱")){
            flight.setF_s_n(flight.getF_s_n()+1);
        }else{
            flight.setF_j_n(flight.getF_j_n()+1);
        }
        flightService.updateByF_type(flight);
        //退票处理
        int i = orderService.returnFlight(order_id);

        /*model.addAttribute("userFlightList", orderService.findByUserName(username));
        return "user/order_list";*/
        return "redirect:/user/getMyTicket";
    }

    //用户前往改签页面
    @GetMapping("/findOrder/{order_id}")
    public String findOrder(@PathVariable("order_id") Integer order_id, RedirectAttributes attributes, HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        Order order=orderService.findByUserOrderId(order_id);
        Flight flight=flightService.findAllById(order.getF_id());
        model.addAttribute("flight",flight);
        model.addAttribute("order",order);
        return "user/change_order";
    }

    //更换舱位操作
    @PostMapping("/changeOrder")
    public String changeOrder(Integer order_id, Integer f_id, String f_type, String f_type_c, Model model, HttpSession session, RedirectAttributes attributes){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        Flight flight=flightService.findAllById(f_id);
        Order order=orderService.findByUserOrderId(order_id);
        if(f_type.equals(f_type_c))//这里不能使用“==”比较字符串
            return "redirect:/user/getMyTicket";
        else if(f_type_c.equals("头等舱"))
        {
            if(flight.getF_t_n()<=0)
            {
                model.addAttribute("order",order);
                return "user/failToChangeF_type";
            }
            else {
                order.setF_type(f_type_c);
                order.setF_price(flight.getF_t());
                orderService.updateOrder(order);
                if(f_type.equals("头等舱"))
                    flight.setF_t_n(flight.getF_t_n()+1);
                else if(f_type.equals("商务舱"))
                    flight.setF_s_n(flight.getF_s_n()+1);
                else if(f_type.equals("经济舱"))
                    flight.setF_j_n(flight.getF_j_n()+1);
                flight.setF_t_n(flight.getF_t_n()-1);
                flightService.updateFlight(flight);
                return "redirect:/user/getMyTicket";
            }

        }
        else if(f_type_c.equals("商务舱"))
        {
            if(flight.getF_s_n()<=0)
            {
                model.addAttribute("order",order);
                return "user/failToChangeF_type";
            }
            else {
                order.setF_type(f_type_c);
                order.setF_price(flight.getF_s());
                orderService.updateOrder(order);
                if(f_type.equals("头等舱"))
                    flight.setF_t_n(flight.getF_t_n()+1);
                else if(f_type.equals("商务舱"))
                    flight.setF_s_n(flight.getF_s_n()+1);
                else if(f_type.equals("经济舱"))
                    flight.setF_j_n(flight.getF_j_n()+1);
                flight.setF_s_n(flight.getF_s_n()-1);
                flightService.updateFlight(flight);
                return "redirect:/user/getMyTicket";
            }
        }
        else /*if(f_type_c.equals("经济舱"))*/
        {
            if(flight.getF_j_n()<=0)
            {
                model.addAttribute("order",order);
                return "user/failToChangeF_type";
            }
            else {
                order.setF_type(f_type_c);
                order.setF_price(flight.getF_j());
                orderService.updateOrder(order);
                if(f_type.equals("头等舱"))
                    flight.setF_t_n(flight.getF_t_n()+1);
                else if(f_type.equals("商务舱"))
                    flight.setF_s_n(flight.getF_s_n()+1);
                else if(f_type.equals("经济舱"))
                    flight.setF_j_n(flight.getF_j_n()+1);
                flight.setF_j_n(flight.getF_j_n()-1);
                flightService.updateFlight(flight);
                return "redirect:/user/getMyTicket";
            }

        }
        /*else
            return "user/error";*/
    }

    //前往chooseOtherFlight页面
    @GetMapping("/chooseOtherFlight/{order_id}")
    public String chooseOtherFlight(@PathVariable("order_id")Integer order_id, Model model, HttpSession session,RedirectAttributes attributes) {
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        Order order = orderService.findByUserOrderId(order_id);
        Flight flight=flightService.findAllById(order.getF_id());
        model.addAttribute("flightList",flightService.findByF_startAndF_endAndOtherF_dt(flight.getF_start(),flight.getF_end(),flight.getF_dt()));
        model.addAttribute("order",order);
        return "user/chooseOtherFlight";
    }
    //改签操作
    @GetMapping("/reviseOrder/{order_id}/{f_id}")
    public String reviseOrder(@PathVariable("order_id") Integer order_id,@PathVariable("f_id")Integer f_id,Model model,HttpSession session,RedirectAttributes attributes){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        Order order=orderService.findByUserOrderId(order_id);
        Flight flight0=flightService.findAllById(order.getF_id());
        Flight flight=flightService.findAllById(f_id);
        if(flight.getF_t_n()<=0&&order.getF_type().equals("头等舱")||flight.getF_s_n()<=0&&order.getF_type().equals("商务舱")||flight.getF_j_n()<=0&&order.getF_type().equals("经济舱")) {
            model.addAttribute("order", order);
            return "user/failToRevise";
        }
        if(flight.getF_t_n()>0&&order.getF_type().equals("头等舱"))
        {
            order.setF_id(flight.getF_id());
            order.setF_num(flight.getF_num());
            order.setF_price(flight.getF_t());
            flight0.setF_t_n(flight0.getF_t_n()+1);
            flight.setF_t_n(flight.getF_t_n()-1);
        }
        if(flight.getF_s_n()>0&&order.getF_type().equals("商务舱"))
        {
            order.setF_id(flight.getF_id());
            order.setF_num(flight.getF_num());
            order.setF_price(flight.getF_s());
            flight0.setF_s_n(flight0.getF_s_n()+1);
            flight.setF_s_n(flight.getF_s_n()-1);
        }
        if(flight.getF_j_n()>0&&order.getF_type().equals("经济舱"))
        {
            order.setF_id(flight.getF_id());
            order.setF_num(flight.getF_num());
            order.setF_price(flight.getF_j());
            flight0.setF_j_n(flight0.getF_j_n()+1);
            flight.setF_j_n(flight.getF_j_n()-1);
        }
        orderService.updateOrder(order);
        flightService.updateFlight(flight0);
        flightService.updateFlight(flight);
        session.setAttribute("message","改签成功！");
        return "redirect:/user/getMyTicket";

    }




    //删除并重新返回首页选择订单
    @GetMapping("/deleteOrderAndBackToIndex/{order_id}")
    public String deleteOrderAndBackToIndex(@PathVariable("order_id") Integer order_id, Model model, RedirectAttributes attributes, HttpSession session){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }

        Order order = orderService.findByUserOrderId(order_id);
        String f_type = order.getF_type();
        /* Flight flight = flightService.findAllByF_num(byUserOrderId.getF_num());*/
        Flight flight=flightService.findAllById(order.getF_id());

        if(f_type.equals("头等舱")){
            flight.setF_t_n(flight.getF_t_n()+1);
        }else if(f_type.equals("商务舱")){
            flight.setF_s_n(flight.getF_s_n()+1);
        }else{
            flight.setF_j_n(flight.getF_j_n()+1);
        }
        flightService.updateByF_type(flight);
        //退票处理
        int i = orderService.returnFlight(order_id);

        /*model.addAttribute("userFlightList", orderService.findByUserName(username));
        return "user/order_list";*/
        return "redirect:/";
    }
    /**
     * 买票，将库存减一
     * @param session
     * @param model
     * @param attributes
     * @param flight_id
     *
     * @param grade
     * @param passenger_id
     * @param contact_phone
     * @return
     */
    @PostMapping("/buyOneTicket")
    public String buyOneTicket(HttpSession session, Model model, RedirectAttributes attributes,RedirectAttributes redirectAttributes,
                               int flight_id, /*String date, */String grade,
                               String passenger_id, String contact_phone) {
        //判断登录状态
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }

        //拿到对应的航班信息
/*        Flight flight = flightService.findAllByF_num(flight_id);*/
        Flight flight=flightService.findAllById(flight_id);

        //判断是否有剩余舱位
        if((grade.equals("头等舱")&&flight.getF_t_n()<=0)||(grade.equals("商务舱")&&flight.getF_s_n()<=0)||(grade.equals("经济舱")&&flight.getF_j_n()<=0)) {
            /*attributes.addFlashAttribute("message","当前所选订单舱位已为空，请选择其他舱位或订单");*/
            //
            model.addAttribute("message","当前所选订单舱位已为空，请选择其他舱位或订单");
            model.addAttribute("flight",flight);
            User user=userService.FindUserByUsername(username);
            model.addAttribute("user",user);
            return "default/showFlight";
        }
        //创建一个订单信息，并设置订单的详细信息
        Order order = new Order();
//        order.setPassenger(passenger_name);
        order.setF_num(flight.getF_num());
        order.setUsername(username);
        order.setUserID(passenger_id);
        /*order.setO_date(date);*/
        order.setF_type(grade);
        order.setPhone(contact_phone);
//        order.setF_start(flight.getF_start());
//        order.setF_end(flight.getF_end());
        order.setF_id(flight_id);
        order.setId(userService.FindUserByUsername(username).getId());

        //根据选择的舱类型设置价格，并将舱位减一
        if(grade.equals("头等舱")){
            order.setF_price(flight.getF_t());
            flight.setF_t_n(flight.getF_t_n()-1);
        }else if(grade.equals("商务舱")){
            order.setF_price(flight.getF_s());
            flight.setF_s_n(flight.getF_s_n()-1);
        }else{
            order.setF_price(flight.getF_j());
            flight.setF_j_n(flight.getF_j_n()-1);
        }


        //订单生成成功
        orderService.saveOrder(order);
        flightService.updateByF_type(flight);
        model.addAttribute("flight",flight);
        User user=userService.FindUserByUsername(username);
        model.addAttribute("user",user);
        model.addAttribute("successMessage","订购成功！");
        return "default/showFlight";

    }


    //用户注销自己的账户
    @GetMapping("/deleteUser")
    public String deleteUser(HttpSession session, RedirectAttributes attributes){
        String username = (String) session.getAttribute("username");
        if(username == null) {
            attributes.addFlashAttribute("message","您无权限访问，请先登录");
            return "redirect:/user";
        }
        User user=(User)session.getAttribute("user");
        userService.deleteUserById(user.getId());
        return "redirect:/user";
    }



}
