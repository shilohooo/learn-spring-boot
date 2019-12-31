package org.shiloh.app.config.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 20:11
 * @description 自定义认证成功的处理器
 */
@Component
@Slf4j
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private ClientDetailsService clientDetailsService;

    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 从请求头中获取client_id
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic")) {
            throw new UnapprovedClientAuthenticationException("请求头中没有client信息");
        }
        String[] tokens = this.extractAndDecodeHeader(header, request);
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        TokenRequest tokenRequest;
        // 获取ClientDetails
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        // 校验clientId和clientSecret是否正确
        if (clientDetails == null) {
            throw new UnapprovedClientAuthenticationException(String.format("client：%1$s对应的信息不存在", clientId));
        } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
            throw new UnapprovedClientAuthenticationException("clientSecret不正确");
        } else {
            // 生成tokenRequest
            tokenRequest = new TokenRequest(new HashMap<>(), clientId, clientDetails.getScope(), "custom");
        }
        // 通过tokenRequest的createOAuth2Request()方法获取OAuth2Request
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        // 获取OAuth2Authentication
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        // 生成OAuth2AccessToken
        OAuth2AccessToken accessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
        // 将token返回
        log.info("登录成功");
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(new ObjectMapper().writeValueAsString(accessToken));
    }

    /**
     * 获取请求头中的token并进行解密
     * @author lxlei
     * @param header
     * @param request
     * @return java.lang.String[]
     **/
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) {
        byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
        byte[] decodedStr;
        try {
            decodedStr = Base64.getDecoder().decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("解析认证令牌失败");
        }
        String token = new String(decodedStr, StandardCharsets.UTF_8);
        int colonIndex = token.indexOf(":");
        if (colonIndex == -1) {
            throw new BadCredentialsException("无效的认证令牌");
        } else {
            return new String[]{token.substring(0, colonIndex), token.substring(colonIndex + 1)};
        }
    }
}
