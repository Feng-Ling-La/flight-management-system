package com.hxh.bean;

public class Order {

   private Integer order_id;
   //对应用户表的 id 是外键
   private Integer id;

   private String f_num;
   private String username;
   private String userID;
   private String o_date;
   private String f_type;
   private String phone;
   private String f_start;
   private String f_end;
   private Integer f_price;
   private Integer o_flag;

   private String f_dt;
   private String f_at;

   //对应 航班表的 f_id 是外键
   private Integer f_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }



    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public String getF_num() {
        return f_num;
    }

    public void setF_num(String f_num) {
        this.f_num = f_num;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getO_date() {
        return o_date;
    }

    public void setO_date(String o_date) {
        this.o_date = o_date;
    }

    public String getF_type() {
        return f_type;
    }

    public void setF_type(String f_type) {
        this.f_type = f_type;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getF_start() {
        return f_start;
    }

    public void setF_start(String f_start) {
        this.f_start = f_start;
    }

    public String getF_end() {
        return f_end;
    }

    public void setF_end(String f_end) {
        this.f_end = f_end;
    }

    public Integer getF_price() {
        return f_price;
    }

    public void setF_price(Integer f_price) {
        this.f_price = f_price;
    }

    public Integer getO_flag() {
        return o_flag;
    }

    public void setO_flag(Integer o_flag) {
        this.o_flag = o_flag;
    }

    //f_dt和f_at的get和set方法
    public String getF_dt() {
        return f_dt;
    }

    public void setF_dt(String f_dt) {
        this.f_dt = f_dt;
    }

    public String getF_at() {
        return f_at;
    }

    public void setF_at(String f_at) {
        this.f_at = f_at;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", id=" + id +
                ", f_num='" + f_num + '\'' +
                ", username='" + username + '\'' +
                ", userID='" + userID + '\'' +
                ", o_date='" + o_date + '\'' +
                ", f_type='" + f_type + '\'' +
                ", phone='" + phone + '\'' +
                ", f_start='" + f_start + '\'' +
                ", f_end='" + f_end + '\'' +
                ", f_price=" + f_price +
                ", o_flag=" + o_flag +
                ", f_dt='" + f_dt + '\'' +
                ", f_at='" + f_at + '\'' +
                ", f_id=" + f_id +
                '}';
    }

    /*@Override
    public String toString() {
        return "Order{" +
                "order_id=" + order_id +
                ", id=" + id +
                ", f_num='" + f_num + '\'' +
                ", username='" + username + '\'' +
                ", userID='" + userID + '\'' +
                ", o_date='" + o_date + '\'' +
                ", f_type='" + f_type + '\'' +
                ", phone='" + phone + '\'' +
                ", f_start='" + f_start + '\'' +
                ", f_end='" + f_end + '\'' +
                ", f_price=" + f_price +
                ", o_flag=" + o_flag +
                ", f_id=" + f_id +
                '}';
    }*/
}
