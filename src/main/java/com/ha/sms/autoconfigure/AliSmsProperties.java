package com.ha.sms.autoconfigure;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 阿里云短信属性配置文件
 *
 * @author cjx
 */
@Data
@ConfigurationProperties(prefix = "ha-sms.ali")
public class AliSmsProperties {

    /**
     * 短信API产品名称
     */
    private String product = "Dysmsapi";

    /**
     * 短信API产品域名
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * 你的accessKeyId
     */
    private String accessKeyId;

    /**
     * 你的accessKeySecret
     */
    private String accessKeySecret;

    /**
     * 短信签名-可在短信控制台中找到
     */
    private String signName;

    /**
     * 是否启用
     */
    private Boolean enable = false;
}
