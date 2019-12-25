package org.shiloh.app.common.config.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shiloh
 * @Date Created in 2019/12/25 16:19
 * @description 自定义拦截器，用于计算请求耗时
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
    /**
     * 在处理拦截之前执行
     * @return boolean
     **/
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("处理拦截之前");
        request.setAttribute("startTime", System.currentTimeMillis());
        System.out.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.out.println(((HandlerMethod) handler).getMethod().getName());
        return true;
    }

    /**
     * 当被拦截的方法没有抛出异常成功时才会处理
     * @return void
     **/
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("开始处理拦截");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println(String.format("拦截器处理耗时: [%1$s]", (System.currentTimeMillis() - startTime)));
    }

    /**
     * 无论被拦截的方法抛出异常与否都会执行
     * @return void
     **/
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("处理拦截之后");
        Long startTime = (Long) request.getAttribute("startTime");
        System.out.println(String.format("拦截器处理耗时：[%1$s]", (System.currentTimeMillis() - startTime)));
        System.out.println(String.format("异常信息：[%1$s]", ex));
    }
}
