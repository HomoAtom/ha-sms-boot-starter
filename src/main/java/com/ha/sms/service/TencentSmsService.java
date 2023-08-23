package com.ha.sms.service;

/**
 * 腾讯云短信service
 *
 * @author cjx
 */
public interface TencentSmsService {

    /**
     * 发送验证码
     *
     * @param phone          手机号
     * @param templateId     模板ID
     * @param templateParams 模板参数
     * @return
     */
    String send(String phone, String templateId, String[] templateParams);
}
