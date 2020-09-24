package com.xm.service.impl;

import com.xm.dao.OrderMapper;
import com.xm.entity.PageBean;
import com.xm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageBean<HashMap<String, Object>> getAllOrder(String name, int typeid, int page, int pagesize) {
        List<HashMap<String, Object>> list = orderMapper.getAllOrder(page, pagesize);

        PageBean<HashMap<String, Object>> pb = new PageBean<>();
        pb.setPage(page);
        pb.setList(list);
        int rowcount = rowcount(name, typeid);
        if (rowcount % pagesize == 0) {
            pb.setPages(rowcount / pagesize);
        }else {
            pb.setPages(rowcount / pagesize + 1);
        }
        return pb;
    }
    private int rowcount(String name, int typeId){
        return orderMapper.getRowcount(name);
    }
}
