package com.ha.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.ha.sms.autoconfigure.AliSmsProperties;
import com.ha.sms.bean.SendResponse;
import com.ha.sms.service.AliSmsService;

/**
 * 阿里云短信service
 *
 * @author cjx
 */
public class AliSmsServiceImpl implements AliSmsService {

    /**
     * 发送短信成功状态
     */
    private static final String OK = "OK";

    /**
     * 阿里云短信属性配置文件
     */
    private AliSmsProperties aliSmsProperties;

    /**
     * SMS的client对象
     */
    private IAcsClient acsClient;

    /**
     * 配置方法
     *
     * @param aliSmsProperties 阿里云短信属性配置文件
     */
    public void config(AliSmsProperties aliSmsProperties) throws ClientException {
        this.aliSmsProperties = new AliSmsProperties();
        BeanUtil.copyProperties(aliSmsProperties, this.aliSmsProperties, CopyOptions.create().setIgnoreNullValue(true));
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", aliSmsProperties.getAccessKeyId(), aliSmsProperties.getAccessKeySecret());
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", aliSmsProperties.getProduct(), aliSmsProperties.getDomain());
        acsClient = new DefaultAcsClient(profile);
    }

    /**
     * 获取SendSmsRequest
     *
     * @return
     */
    private SendSmsRequest getSendSmsRequest() {
        SendSmsRequest request = new SendSmsRequest();
        request.setSignName(aliSmsProperties.getSignName());
        return request;
    }

    /**
     * 发送验证码
     *
     * @param phone         手机号
     * @param templateCode  短信模板-可在短信控制台中找到
     * @param templateParam 模板中的变量替换JSON串
     * @return
     */
    @Override
    public String send(String phone, String templateCode, String templateParam) {
        try {

            SendSmsRequest request = getSendSmsRequest();
            //必填:待发送手机号
            request.setPhoneNumbers(phone);
            //必填:短信模板-可在短信控制台中找到
            request.setTemplateCode(templateCode);
            //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
            request.setTemplateParam(templateParam);

            //hint 此处可能会抛出异常，注意catch
            SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
            //请求成功
            if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals(OK)) {
                return SendResponse.success();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return SendResponse.failure();
    }

}
