package com.ha.sms.autoconfigure;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.ReflectUtil;
import com.aliyuncs.exceptions.ClientException;
import com.ha.sms.service.AliSmsService;
import com.ha.sms.service.TencentSmsService;
import com.ha.sms.service.impl.AliSmsServiceImpl;
import com.ha.sms.service.impl.TencentSmsServiceImpl;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 通用短信自动装配类
 *
 * @author cjx
 */
@Configuration
@EnableConfigurationProperties({TencentSmsProperties.class, AliSmsProperties.class})
public class SmsAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ha-sms.tencent", name = "enable", havingValue = "true")
    public TencentSmsService tencentSmsService(TencentSmsProperties tencentSmsProperties) {
        TencentSmsServiceImpl tencentSmsService = new TencentSmsServiceImpl();
        tencentSmsService.config(getProperties(tencentSmsProperties, TencentSmsProperties.class));
        return tencentSmsService;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "ha-sms.ali", name = "enable", havingValue = "true")
    public AliSmsService aliSmsService(AliSmsProperties aliSmsProperties) throws ClientException {
        AliSmsServiceImpl aliSmsService = new AliSmsServiceImpl();
        aliSmsService.config(getProperties(aliSmsProperties, AliSmsProperties.class));
        return aliSmsService;
    }

    /**
     * 获取属性配置文件，加此方法是为了保留默认的配置
     *
     * @param properties 属性配置文件
     * @param clazz      属性配置文件的类型，因为泛型擦除机制，只能加个这个参数了
     * @param <T>
     * @return 修改后的属性配置文件
     */
    <T> T getProperties(T properties, Class clazz) {
        try {
            Object obj = ReflectUtil.newInstance(clazz);
            BeanUtil.copyProperties(properties, obj, CopyOptions.create().setIgnoreNullValue(true));
            return (T) obj;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }
}
