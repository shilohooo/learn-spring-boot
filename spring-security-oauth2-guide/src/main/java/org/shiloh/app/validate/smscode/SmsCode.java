package org.shiloh.app.validate.smscode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
