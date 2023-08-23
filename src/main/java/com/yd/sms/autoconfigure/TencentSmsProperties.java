package com.yd.sms.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 腾讯云短信属性配置文件
 *
 * @author cjx
 */
@Data
@ConfigurationProperties(prefix = "ha-sms.tencent")
public class TencentSmsProperties {

    /**
     * 短信应用 ID
     */
    private String appId;

    /**
     * 腾讯云账户密钥对 secretId
     */
    private String secretId;

    /**
     * 腾讯云账户密钥对 secretKey
     */
    private String secretKey;

    /**
     * 短信签名内容
     */
    private String sign;

    /**
     * 是否启用
     */
    private Boolean enable = false;
}
