package org.shiloh.app.enhancer;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shiloh
 * @Date Created in 2020/1/2 16:09
 * @description Jwt token增强
 */
public class JwtTokenEnhancer implements TokenEnhancer {
    /**
     * 在Jwt token中加入一些额外的数据
     * @author lxlei
     * @return org.springframework.security.oauth2.common.OAuth2AccessToken
     **/
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {
        Map<String, Object> map = new HashMap<>(16);
        map.put("msg", "Hello OAuth2");
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(map);
        return oAuth2AccessToken;
    }
}
