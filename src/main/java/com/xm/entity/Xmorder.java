package com.xm.entity;


public class Xmorder {

  private String oid;
  private long customerid;
  private long addressId;
  private double totalprice;
  private String remarks;
  private String status;
  private java.sql.Timestamp odate;


  public String getOid() {
    return oid;
  }

  public void setOid(String oid) {
    this.oid = oid;
  }


  public long getCustomerid() {
    return customerid;
  }

  public void setCustomerid(long customerid) {
    this.customerid = customerid;
  }


  public long getAddressId() {
    return addressId;
  }

  public void setAddressId(long addressId) {
    this.addressId = addressId;
  }


  public double getTotalprice() {
    return totalprice;
  }

  public void setTotalprice(double totalprice) {
    this.totalprice = totalprice;
  }


  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }


  public java.sql.Timestamp getOdate() {
    return odate;
  }

  public void setOdate(java.sql.Timestamp odate) {
    this.odate = odate;
  }

}
