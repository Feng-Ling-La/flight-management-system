package com.hxh.bean;

/**
 * 对应数据库中的  Flight 表，属性和字段一一对应。
 * 该类中只有 属性和getter/setter方法
 */
public class Flight {
    private Integer f_id;
    private String f_num;
    private String f_start;
    private String f_end;
    private String f_dt;
    private String f_at;
    private Integer f_t;
    private Integer f_s;
    private Integer f_j;
    private Integer f_t_n;
    private Integer f_s_n;
    private Integer f_j_n;

    public Integer getF_id() {
        return f_id;
    }

    public void setF_id(Integer f_id) {
        this.f_id = f_id;
    }

    public String getF_num() {
        return f_num;
    }

    public void setF_num(String f_num) {
        this.f_num = f_num;
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

    public Integer getF_t() {
        return f_t;
    }

    public void setF_t(Integer f_t) {
        this.f_t = f_t;
    }

    public Integer getF_s() {
        return f_s;
    }

    public void setF_s(Integer f_s) {
        this.f_s = f_s;
    }

    public Integer getF_j() {
        return f_j;
    }

    public void setF_j(Integer f_j) {
        this.f_j = f_j;
    }

    public Integer getF_t_n() {
        return f_t_n;
    }

    public void setF_t_n(Integer f_t_n) {
        this.f_t_n = f_t_n;
    }

    public Integer getF_s_n() {
        return f_s_n;
    }

    public void setF_s_n(Integer f_s_n) {
        this.f_s_n = f_s_n;
    }

    public Integer getF_j_n() {
        return f_j_n;
    }

    public void setF_j_n(Integer f_j_n) {
        this.f_j_n = f_j_n;
    }
}
