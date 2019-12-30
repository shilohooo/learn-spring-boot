package org.shiloh.app.validate.code;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.io.Serializable;
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
@JsonIgnoreProperties({"image"})
public class ImageCode implements Serializable {

    private static final long serialVersionUID = -88344033813453764L;

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

    public ImageCode(BufferedImage image, String code, Integer expireIn) {
        this.image = image;
        this.code = code;
        // 设置验证码过期时间，单位：秒
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
