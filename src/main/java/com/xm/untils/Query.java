package com.xm.untils;

/**
 * @author lz
 * @date 2020/9/21 - 10:13
 * @function
 */
public class Query {

    private Integer pn = 1;//页码
    private Integer ps = 5;//每页记录数

    public Query(Integer pn, Integer ps) {
        this.setPn(pn);
        this.setPs(ps);
    }

    public Integer getPn() {
        return pn;
    }

    public void setPn(Integer pn) {
        if (pn != null && pn >= 1) {
            this.pn = pn;
        }
    }

    public Integer getPs() {
        return ps;
    }

    public void setPs(Integer ps) {
        if (ps != null && ps > 0 && ps <= 50) {
            this.ps = ps;
        }
    }

    public int getOffset() {
        return pn*ps-ps;
    }

}
