package com.xm.controller;


import com.xm.entity.Customer;
import com.xm.service.CustomerService;
import com.xm.untils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    //进入客户注册页面,用户进行注册
    @GetMapping("/toregisterpage")
    public String toRePage(){

        return "register";//register.jsp
    }

    @PostMapping("/docheckcname")
    @ResponseBody
    public Map<String,String> doCheckName(String cname){

        Map<String,String> result=new HashMap<>();
        Customer c = customerService.getCustomerByCname(cname);
        if(Objects.isNull(c)){//c为空没有查询到
            result.put("info","可以使用");

        }else{
            result.put("info","不能使用");
        }

        return result;//info输出的内容，显示在页面上
    }

    //跳转用户登录
    @GetMapping("/tocustomerloginpage")
    public String toLoginPage(){

        return "customerlogin";

    }

    //登陆业务
    @PostMapping("/customerlogin")
    public String doCustomerLogin(String cname,String cpass,HttpSession session){
        Customer customer=customerService.login(cname, MD5Util.getMd5Str(cpass));
        session.setAttribute("customer",customer);
        return"redirect:/front/index";
    }

    @PostMapping("/doregister")
    public String doRegister(Customer customer, String yzm, HttpSession session, Model model){

        String rdmCode=(String)session.getAttribute("rdmCode");

        if(!rdmCode.equalsIgnoreCase(yzm)){
            model.addAttribute("error","验证码错误");
            return "register";
        }
        //将密码存放到工具中
        customer.setCpass(MD5Util.getMd5Str(customer.getCpass()));
        customerService.registerCustomer(customer);
            //成功进入登陆界面
        return "customerlogin";
    }

}
