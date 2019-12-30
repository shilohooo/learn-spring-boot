package org.shiloh.app.validate.sms.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 10:31
 * @description 短信验证码实体
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SmsCode {

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public SmsCode(String code, Integer expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断验证码是否已过期
     * @author lxlei
     * @return java.lang.Boolean
     **/
    Boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }

}
