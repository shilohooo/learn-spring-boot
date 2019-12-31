package org.shiloh.app.common.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.shiloh.app.validate.smscode.SmsCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * @author shiloh
 * @Date Created in 2019/12/31 9:27
 * @description redis操作验证码
 */
@Service
@Slf4j
public class RedisCodeService {

    /**
     * 短信验证码前缀
     */
    private static final String SMS_CODE_PREFIX = "SMS_CODE";

    /**
     * 过期时间，单位：秒
     */
    private static final Integer TIMEOUT = 300;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 保存验证码到redis
     * @author lxlei
     * @param smsCode 短信验证码
     * @param servletWebRequest
     * @param mobile 手机号码
     **/
    public void save(SmsCode smsCode, ServletWebRequest servletWebRequest, String mobile) {
        try {
            String key = key(servletWebRequest, mobile);
            System.out.println("set key = " + key);
            redisTemplate.opsForValue().set(key, smsCode.getCode(), TIMEOUT, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.info("存储验证码失败：[{}]", e.getMessage());
        }
    }

    /**
     * 获取验证码
     * @author lxlei
     * @param servletWebRequest
     * @param mobile
     * @return java.lang.String
     **/
    public String getSmsCode(ServletWebRequest servletWebRequest, String mobile) {
        try {
            String key = key(servletWebRequest, mobile);
            System.out.println("get key = " + key);
            return redisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            return String.format("获取验证码失败：%1$s", e.getMessage());
        }
    }

    /**
     * 移除验证码
     * @author lxlei
     * @param servletWebRequest
     * @param mobile
     **/
    public void removeSmsCode(ServletWebRequest servletWebRequest, String mobile) {
        try {
            redisTemplate.delete(key(servletWebRequest, mobile));
        } catch (Exception e) {
            log.info("移除验证码失败：[{}]", e.getMessage());
        }
    }

    /**
     * 获取key
     * @author lxlei
     * @param request
     * @param mobile
     * @return java.lang.String
     **/
    private String key(ServletWebRequest request, String mobile) throws Exception {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new Exception("请在请求头中设置deviceId");
        }
        return String.format("%1$s:%2$s%3$S", SMS_CODE_PREFIX, deviceId, mobile);
    }
}
