package com.ha.sms.service;

/**
 * 阿里云短信service
 *
 * @author cjx
 */
public interface AliSmsService {

    /**
     * 发送验证码
     *
     * @param phone         手机号
     * @param templateCode  短信模板-可在短信控制台中找到
     * @param templateParam 模板中的变量替换JSON串
     * @return
     */
    String send(String phone, String templateCode, String templateParam);
}
