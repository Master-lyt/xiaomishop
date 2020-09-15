package com.xm.entity;


public class Orderdetail {

  private long odid;
  private String oid;
  private long pid;
  private long pnumber;
  private double ptotal;


  public long getOdid() {
    return odid;
  }

  public void setOdid(long odid) {
    this.odid = odid;
  }


  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }


  public long getPid() {
    return pid;
  }

  public void setPid(long pid) {
    this.pid = pid;
  }


  public long getPnumber() {
    return pnumber;
  }

  public void setPnumber(long pnumber) {
    this.pnumber = pnumber;
  }


  public double getPtotal() {
    return ptotal;
  }

  public void setPtotal(double ptotal) {
    this.ptotal = ptotal;
  }

}
