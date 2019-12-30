package org.shiloh.app.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shiloh
 * @Date Created in 2019/12/29 23:08
 * @description 自定义处理登录失败逻辑
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Autowired
    private ObjectMapper mapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException e) throws IOException, ServletException {
        // 设置响应状态码为500，即为系统内部异常
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        // 设置响应内容类型
        response.setContentType("application/json;charset=utf-8");
        // 输出返回异常信息
        response.getWriter().write(mapper.writeValueAsString(e.getMessage()));
    }
}
