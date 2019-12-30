package org.shiloh.app.validate.code;

import lombok.*;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author shiloh
 * @Date Created in 2019/12/30 9:12
 * @description 图片验证码实体
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ImageCode {

    /**
     * 验证码图片
     */
    private BufferedImage image;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private LocalDateTime expireTime;

    public ImageCode(BufferedImage image, String code, int expireIn) {
        this.image = image;
        this.code = code;
        // 设置验证码过期时间，单位：秒
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /**
     * 判断验证码是否过期
     * @author lxlei
     * @return java.lang.Boolean
     **/
    Boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
