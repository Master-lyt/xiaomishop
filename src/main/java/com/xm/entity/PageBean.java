package com.xm.entity;

import java.util.List;

/**
 * @author lz
 * @date 2020/9/16 - 8:59
 * @function
 */
public class PageBean<T> {

    // 当前的页码
    private int page;
    // 总行数
    private int rowcount;
    // 每页显示数
    private int pagesize;
    // 当前页的数据
    private List<T> list;
    // 总的页数
    private int pages;//总的页数=rowcount/pagesize   整除10/5 = 2  不整除11/5 + 1 = 3

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowcount() {
        return rowcount;
    }

    public void setRowcount(int rowcount) {
        this.rowcount = rowcount;
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getPages() {
        return pages;
    }

    // 获取总的页数
    public void setPages(int pages) {
        this.pages = pages;
    }

    @Override
    public String toString() {
        return "PageBean [page=" + page + ", rowcount=" + rowcount + ", pagesize=" + pagesize + ", list=" + list
                + ", pages=" + pages + "]";
    }

}
