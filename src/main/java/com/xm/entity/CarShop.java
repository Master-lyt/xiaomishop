package com.xm.entity;

/**
 * @author lz
 * @date 2020/9/15 - 15:35
 * @function
 */
public class CarShop {

    private Integer id;
    private Integer customerid;
    private Integer productid;
    private Integer numbers;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public Integer getNumbers() {
        return numbers;
    }

    public void setNumbers(Integer numbers) {
        this.numbers = numbers;
    }

    public CarShop() {
    }

    public CarShop(Integer id, Integer customerid, Integer productid, Integer numbers) {
        this.id = id;
        this.customerid = customerid;
        this.productid = productid;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "CarShop{" +
                "id=" + id +
                ", customerid=" + customerid +
                ", productid=" + productid +
                ", numbers=" + numbers +
                '}';
    }
}
