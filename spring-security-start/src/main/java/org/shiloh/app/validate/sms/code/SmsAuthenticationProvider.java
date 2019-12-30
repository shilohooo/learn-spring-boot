package org.shiloh.app.validate.sms.code;

import lombok.Setter;
import org.shiloh.app.config.MyUserDetailsService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 11:29
 * @description 自定义处理SmsAuthenticationToken类型的token的provider
 */
@Setter
public class SmsAuthenticationProvider implements AuthenticationProvider {

    private MyUserDetailsService myUserDetailsService;

    /**
     * 具体的身份认证逻辑
     * @author lxlei
     * @param authentication
     * @return org.springframework.security.core.Authentication
     **/
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SmsAuthenticationToken authenticationToken = (SmsAuthenticationToken) authentication;
        UserDetails userDetails = myUserDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (userDetails == null) {
            throw new InternalAuthenticationServiceException("未找到与该手机号码对应的用户");
        }
        SmsAuthenticationToken tokenResult = new SmsAuthenticationToken(userDetails, userDetails.getAuthorities());
        tokenResult.setDetails(authenticationToken.getDetails());
        return tokenResult;
    }

    /**
     * 指定要处理的token类型
     * @author lxlei
     * @param aClass token类型
     * @return boolean
     **/
    @Override
    public boolean supports(Class<?> aClass) {
        return SmsAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
