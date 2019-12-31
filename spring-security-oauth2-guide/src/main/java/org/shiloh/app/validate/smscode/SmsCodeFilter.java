package org.shiloh.app.validate.smscode;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.shiloh.app.common.service.RedisCodeService;
import org.shiloh.app.config.security.handler.MyAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 11:35
 * @description 自定义短信验证码校验过滤器
 */
@Component
@Slf4j
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private MyAuthenticationFailureHandler failureHandler;

    @Autowired
    private RedisCodeService redisCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/sms_login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                validateSmsCode(new ServletWebRequest(request));
            } catch (Exception e) {
                failureHandler.onAuthenticationFailure(request, response, new AuthenticationServiceException(e.getMessage()));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 短信验证码校验
     * @author lxlei
     * @param servletWebRequest
     **/
    private void validateSmsCode(ServletWebRequest servletWebRequest) throws Exception {
        try {
            // 获取用户输入的短信验证码
            String smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
            // 获取用户输入的手机号码
            String mobileInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
            String codeInRedis = redisCodeService.getSmsCode(servletWebRequest, mobileInRequest);
            if (StringUtils.isBlank(smsCodeInRequest)) {
                throw new Exception("短信验证码不能为空！");
            }
            if (codeInRedis == null) {
                throw new Exception("短信验证码已过期！");
            }
            if (!StringUtils.equalsIgnoreCase(codeInRedis, smsCodeInRequest)) {
                throw new Exception("短信验证码输入错误！");
            }
            // 校验通过之后将验证码从session中移除
            redisCodeService.removeSmsCode(servletWebRequest, mobileInRequest);
        } catch (ServletRequestBindingException e) {
            log.info("短信验证码校验失败：[{}]", e.getMessage());
        }
    }
}
