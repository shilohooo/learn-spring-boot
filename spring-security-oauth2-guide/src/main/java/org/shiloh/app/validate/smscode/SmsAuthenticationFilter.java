package org.shiloh.app.validate.smscode;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 11:07
 * @description 自定义短信认证过滤器
 */
public class SmsAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    public static final String MOBILE_KEY = "mobile";

    private String mobileParam = MOBILE_KEY;

    private Boolean postOnly = true;

    public SmsAuthenticationFilter() {
        // 指定在请求url为/sms_login以及请求方式为post时，让过滤器生效
        super(new AntPathRequestMatcher("/sms_login", "POST"));
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,HttpServletResponse response)
            throws AuthenticationException {
        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported" + request.getMethod());
        }
        // 获取页面输入的手机号码
        String mobile = getMobile(request);
        if (mobile == null) {
            mobile = "";
        }
        mobile = mobile.trim();
        // 构建一个token
        SmsAuthenticationToken token = new SmsAuthenticationToken(mobile);
        setDetails(request, token);
        // 将token交给AuthenticationManager处理
        return this.getAuthenticationManager().authenticate(token);
    }

    protected String getMobile(HttpServletRequest request) {
        return request.getParameter(mobileParam);
    }

    protected void setDetails(HttpServletRequest request, SmsAuthenticationToken token) {
        token.setDetails(authenticationDetailsSource.buildDetails(request));
    }

    public void setMobileParam(String mobileParam) {
        Assert.hasText(mobileParam, "mobile param must not be empty or null");
        this.mobileParam = mobileParam;
    }

    public void setPostOnly(Boolean postOnly) {
        this.postOnly = postOnly;
    }

    public final String getMobileParam() {
        return this.mobileParam;
    }
}
