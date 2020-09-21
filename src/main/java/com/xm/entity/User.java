package com.xm.entity;

/**
 * @author lz
 * @date 2020/9/15 - 15:38
 * @function
 */
public class User {

    private Integer uid;
    private String uname;
    private String upass;
    private String udepartment;
    private String realname;
    private Integer roleid;
    private String uimage;

    public String getUimage() {
        return uimage;
    }

    public void setUimage(String uimage) {
        this.uimage = uimage;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpass() {
        return upass;
    }

    public void setUpass(String upass) {
        this.upass = upass;
    }

    public String getUdepartment() {
        return udepartment;
    }

    public void setUdepartment(String udepartment) {
        this.udepartment = udepartment;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public User() {
    }

    public User(Integer uid, String uname, String upass, String udepartment, String realname, Integer roleid) {
        this.uid = uid;
        this.uname = uname;
        this.upass = upass;
        this.udepartment = udepartment;
        this.realname = realname;
        this.roleid = roleid;
    }

    @Override
    public String toString() {
        return "Users{" +
                "uid=" + uid +
                ", uname='" + uname + '\'' +
                ", upass='" + upass + '\'' +
                ", udepartment='" + udepartment + '\'' +
                ", realname='" + realname + '\'' +
                ", roleid=" + roleid +
                '}';
    }
}
