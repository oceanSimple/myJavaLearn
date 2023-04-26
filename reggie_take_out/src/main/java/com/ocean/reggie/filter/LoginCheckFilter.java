package com.ocean.reggie.filter;

import com.alibaba.fastjson2.JSON;
import com.ocean.reggie.common.BaseContext;
import com.ocean.reggie.common.R;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;

/**
 * 检查用户是否已经完成了登录
 */
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    // 路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();
    // 定义不需要处理的请求路径
    private final String[] urls = new String[] {
            "/employee/login",
            "/employee/logout",
            "/backend/**",
            "/front/**",
            "/common/**"
    };

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取本次请求的URI
        String requestURI = request.getRequestURI();

        //2. 判断本次请求是否需要处理
        boolean check = check(requestURI);

        //3. 如果不需要处理,直接放行
        if (check) {
            filterChain.doFilter(request,response);
            return;
        }

        //4. 如果不成立,获取登录状态,判断是否已经登录
        // 获取session,如果获取到数据,说明已经登录了
        if (request.getSession().getAttribute("employee") != null) {
            System.out.println(request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }

        //5. 如果未登录,通过输出流向客户端页面响应数据
        //ps:返回数据的格式等等由前端规定!
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
    }

    public boolean check(String uri) {
        for (String url : urls) {
            if (PATH_MATCHER.match(url, uri)) {
                return true;
            }
        }
        return false;
    }
}
