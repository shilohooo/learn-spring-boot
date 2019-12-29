package org.shiloh.app.common.config.filter;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:07
 * @description 自定义过滤器，用于计算请求耗时
 */
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("TimeFilter init...");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("TimeFilter begin execution");
        Long startTime = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println(String.format("TimeFilter execute time consuming: %1$sms", (System.currentTimeMillis() - startTime)));
        System.out.println("TimeFilter end of execution");
    }

    @Override
    public void destroy() {
        System.out.println("TimeFilter destroy");
    }
}
