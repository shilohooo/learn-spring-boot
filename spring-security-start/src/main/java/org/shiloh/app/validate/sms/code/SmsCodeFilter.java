package org.shiloh.app.validate.sms.code;

import org.apache.commons.lang3.StringUtils;
import org.shiloh.app.controller.ValidateController;
import org.shiloh.app.validate.code.MyValidateCodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
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
public class SmsCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/sms_login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
            try {
                validateSmsCode(new ServletWebRequest(request));
            } catch (MyValidateCodeException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 短信验证码校验
     * @author lxlei
     * @param servletWebRequest
     * @return void
     **/
    private void validateSmsCode(ServletWebRequest servletWebRequest) {
        try {
            // 获取用户输入的短信验证码
            String smsCodeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "smsCode");
            // 获取用户输入的手机号码
            String mobileInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "mobile");
            SmsCode smsCodeInSession = (SmsCode) sessionStrategy.getAttribute(servletWebRequest,
                    ValidateController.SESSION_KEY_SMS_CODE + mobileInRequest);
            if (StringUtils.isBlank(smsCodeInRequest)) {
                throw new MyValidateCodeException("短信验证码不能为空！");
            }
            if (smsCodeInSession == null) {
                throw new MyValidateCodeException("短信验证码不存在！");
            }
            if (smsCodeInSession.isExpire()) {
                throw new MyValidateCodeException("短信验证码已过期！");
            }
            if (!StringUtils.equalsIgnoreCase(smsCodeInSession.getCode(), smsCodeInRequest)) {
                throw new MyValidateCodeException("短信验证码输入错误！");
            }
            // 校验通过之后将验证码从session中移除
            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_SMS_CODE);
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
    }
}
