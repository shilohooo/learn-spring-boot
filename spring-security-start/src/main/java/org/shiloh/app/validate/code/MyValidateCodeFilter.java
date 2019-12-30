package org.shiloh.app.validate.code;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.shiloh.app.controller.ValidateController;
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
 * @Date Created in 2019/12/30 9:51
 * @description 自己定义验证码校验过滤器,验证码校验通过后采去校验用户名和密码
 */
@Component
@Slf4j
public class MyValidateCodeFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationFailureHandler failureHandler;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        if (StringUtils.equalsIgnoreCase("/login", request.getRequestURI())
                && StringUtils.equalsIgnoreCase(request.getMethod(), "post")) {
            try {
                validateCode(new ServletWebRequest(request));
            } catch (MyValidateCodeException e) {
                failureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 验证码校验
     * @author lxlei
     * @param servletWebRequest
     **/
    private void validateCode(ServletWebRequest servletWebRequest) {
        try {
            // 图片验证码对象
            ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
            // 页面输入的验证码
            String codeInRequest = ServletRequestUtils.getStringParameter(servletWebRequest.getRequest(), "imageCode");
            // 验证
            if (StringUtils.isBlank(codeInRequest)) {
                throw new MyValidateCodeException("验证码不能为空！");
            }
            if (codeInSession == null) {
                throw new MyValidateCodeException("验证码不存在！");
            }
            if (codeInSession.isExpire()) {
                sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
                throw new MyValidateCodeException("验证码已过期！");
            }
            if (!StringUtils.equalsIgnoreCase(codeInSession.getCode(), codeInRequest)) {
                throw new MyValidateCodeException("验证码错误！");
            }
            // 校验通过之后将验证码从session中移除
            sessionStrategy.removeAttribute(servletWebRequest, ValidateController.SESSION_KEY_IMAGE_CODE);
        } catch (ServletRequestBindingException e) {
            log.info("验证码校验异常：[{}]", e.getMessage());
        }
    }
}
