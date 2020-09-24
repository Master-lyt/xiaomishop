package com.xm.controller;

import com.xm.entity.PageBean;
import com.xm.entity.Role;
import com.xm.entity.User;
import com.xm.service.RoleService;
import com.xm.service.UserService;
import com.xm.untils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author lz
 * @date 2020/9/15 - 15:25
 * @function
 */
@Controller
public class UserController {

    //使用spring 注入@Autowired 完成对业务层的注入
    @Autowired
    private UserService usersService;

    @Autowired
    private RoleService roleService;

    //实现加入后台的登录页面/WEB-INF/jsp/login.jsp
    //@RequestMapping(value="/login",method=RequestMethod.GET)
    @GetMapping("login")///login请求的地址：//http://localhost:8080/zhuxiaomishop/login
    public String toLoginPage(){
        return "login";
    }

    /*
     * 实现实际的登录业务
     * 1.实现获取输入的参数 账号uname 和密码 upass
     * 2. 提交请求 /login , 请求的方法为post
     * 3. 需要将参数给业务层的方法
     * 4. 需要将参数给数据访问层的方法
     * 5. 需要查询数据库
     */
    @PostMapping("/login")
    public String userLogin(User user, Model model, HttpSession session){
        //将密码进行加密
        String upass = user.getUpass();
        user.setUpass(MD5Util.getMd5Str(upass));
        //调用业务层
        HashMap<String,Object> urs = usersService.userLogin(user);
        //返回的结果
        if(Objects.isNull(urs)){
            //账号和密码错误，重新回到登录页面，重新登录，需要提示账号或密码错误
            model.addAttribute("info", "账号或密码错误");
            return "login";//逻辑视图名称，/WEB-INF/jsp/逻辑视图名称.jsp ==login.jsp
        }
        //登录成功，进入后台的界面
        session.setAttribute("users", urs);
        return "main";//逻辑视图名称，/WEB-INF/jsp/逻辑视图名称.jsp ==main.jsp
    }

    @GetMapping("logout")
    public String userLogout(HttpSession session){
        session.invalidate();
        return "login";
    }

    @GetMapping("/getusersbypage")
    public String getUsersByPage(@RequestParam(name = "uname", defaultValue = "") String name,
                                 @RequestParam(name = "roleid", defaultValue = "-1") int typeId,
                                 @RequestParam(name = "page", defaultValue = "1") int page, Model model){
        int pagesize = 5;
        PageBean<HashMap<String, Object>> users = usersService.getAllUsersByPage(name, typeId, page, pagesize);
        model.addAttribute("pagebean", users);
        model.addAttribute("rolelist", roleService.getAllRole());
        model.addAttribute("name", name);
        model.addAttribute("roleid", typeId);
        return "usersbypage";
    }

    @GetMapping("delusers")
    @ResponseBody
    public String delUser(@RequestParam(name = "id") int id){
        usersService.delUser(id);
        return "success";
    }

    @GetMapping("batchdelusers")
    @ResponseBody
    public String batchDelUsers(int[] ids){
        usersService.batchDelUsers(ids);
        return "success";
    }

    @GetMapping("getusersbyid")
    public String getUserById(int id, Model model){
        User user = usersService.getUserById(id);
        model.addAttribute("users", user);
        model.addAttribute("rolelist", roleService.getAllRole());
        return "updateusers";
    }

    @PostMapping("/updateusers")
    public String updateUser(User user){
        usersService.updateUser(user);
        return "redirect:/getusersbypage";
    }

    @GetMapping("adduserspage")
    public String toAddUserPage(Model model){
        model.addAttribute("rolelist", roleService.getAllRole());
        return "addusers";
    }

    @PostMapping("addusers")
    public String addUser(User user){
        user.setUpass(MD5Util.getMd5Str(user.getUpass()));
        usersService.addUser(user);
        return "redirect:/getusersbypage";
    }

    @GetMapping("/getusersbypage_ajax")
    @ResponseBody
    public Map<String, Object> getUsersByPage_ajax(@RequestParam(name = "uname", defaultValue = "") String name,
                                                   @RequestParam(name = "roleid", defaultValue = "-1") int typeId,
                                                   @RequestParam(name = "page", defaultValue = "1") int page)  {
        int pagesize = 5;
        System.out.println(name);
        PageBean<HashMap<String, Object>> users = usersService.getAllUsersByPage(name, typeId, page, pagesize);
        Map<String, Object> resultMap = new HashMap();
        resultMap.put("list", users.getList());
        resultMap.put("pages", users.getPages());
        resultMap.put("page", users.getPage());
        resultMap.put("rowcount", users.getRowcount());
        resultMap.put("pagesize", pagesize);
        return resultMap;
    }
}
