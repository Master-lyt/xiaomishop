package com.xm.entity;

/**
 * @author lz
 * @date 2020/9/15 - 15:36
 * @function
 */
public class Customer {

    private Integer cid;
    private String cname;
    private String cpass;
    private String csex;
    private Integer cage;
    private String realname;
    private String phone;

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCpass() {
        return cpass;
    }

    public void setCpass(String cpass) {
        this.cpass = cpass;
    }

    public String getCsex() {
        return csex;
    }

    public void setCsex(String csex) {
        this.csex = csex;
    }

    public Integer getCage() {
        return cage;
    }

    public void setCage(Integer cage) {
        this.cage = cage;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer() {
    }

    public Customer(Integer cid, String cname, String cpass, String csex, Integer cage, String realname, String phone) {
        this.cid = cid;
        this.cname = cname;
        this.cpass = cpass;
        this.csex = csex;
        this.cage = cage;
        this.realname = realname;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "cid=" + cid +
                ", cname='" + cname + '\'' +
                ", cpass='" + cpass + '\'' +
                ", csex='" + csex + '\'' +
                ", cage=" + cage +
                ", realname='" + realname + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
