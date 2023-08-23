package com.yd.sms.utils;

/**
 * 随机数工具类
 *
 * @author cjx
 */
public class RandomUtils {

    /**
     * 随机整数
     *
     * @param len 长度
     * @return
     */
    public static int randomNum(int len) {
        StringBuffer sb = new StringBuffer("1");
        for (int i = 0; i < len; i++) {
            sb.append("0");
        }
        int value = Integer.parseInt(sb.toString());
        return (int) (Math.random() * (value * 0.9) + (value * 0.1));
    }
}
