package com.xm.service;

import com.xm.entity.PageBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface OrderService {
    PageBean<HashMap<String, Object>> getAllOrder(String name, int typeid, int page, int pagesize);
}
