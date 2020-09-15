package com.xm.entity;

/**
 * @author lz
 * @date 2020/9/14 - 15:20
 * @function
 */
public class Address {

    private String addressId;
    private String customerId;
    private String cnee;
    private Integer phone;
    private String address;

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCnee() {
        return cnee;
    }

    public void setCnee(String cnee) {
        this.cnee = cnee;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Address() {
    }

    public Address(String addressId, String customerId, String cnee, Integer phone, String address) {
        this.addressId = addressId;
        this.customerId = customerId;
        this.cnee = cnee;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Address{" +
                "addressId='" + addressId + '\'' +
                ", customerId='" + customerId + '\'' +
                ", cnee='" + cnee + '\'' +
                ", phone=" + phone +
                ", address='" + address + '\'' +
                '}';
    }
}
