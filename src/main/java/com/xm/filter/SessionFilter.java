package com.xm.filter;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author lz
 * @date 2020/9/24 - 21:13
 * @function
 */
public class SessionFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // 请求的uri
        String uri = request.getRequestURI();
        String url = request.getContextPath();
        String loginPage = url + "/login";
        if(!uri.endsWith("zhuxiaomishop/")){
            //放行 login logout randomcode
            if (uri.indexOf("login") == -1 && uri.indexOf("logout") == -1 && uri.indexOf("randomcode") == -1) {
                //其他除了login logout randomcode 的请求，比如getproductbypage 应该被拦截
                //看用户是否登录，登录成功session就保存了登录的用户的信息
                Object obj = request.getSession().getAttribute("users");
                if (null == obj) {
                    //没有登录从session获取不到登录的用户的信息
                    request.setCharacterEncoding("UTF-8");
                    response.setContentType("text/html;charset=UTF-8");
                    PrintWriter out = response.getWriter();
                    StringBuilder builder = new StringBuilder();
                    builder.append("<script type=\"text/javascript\">");
                    builder.append("window.top.location.href='");
                    builder.append(loginPage);
                    builder.append("';");
                    builder.append("alert('页面过期，请重新登录！');");
                    builder.append("</script>");
                    out.print(builder.toString());
                }else{
                    filterChain.doFilter(request, response);
                }
            }else{
                filterChain.doFilter(request, response);
            }
        }else{
            filterChain.doFilter(request, response);
        }
    }

}
