package com.hxh.bean;

/**
 * 对应数据库中的 Admin表，表中各属性对应数据库中的个字段。
 *
 * 该类中只有 属性和getter/setter方法
 */
public class Admin {
    private Integer admin_id;
    private String adminName;
    private String password;

    public Integer getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
