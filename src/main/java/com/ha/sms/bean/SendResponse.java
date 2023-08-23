package com.ha.sms.bean;

/**
 * 发送Response
 *
 * @author cjx
 */
public class SendResponse {

    /**
     * 成功
     */
    private static final String SUCCESS = "success";
    /**
     * 失败
     */
    private static final String FAILURE = "failure";

    /**
     * 成功
     *
     * @return
     */
    public static String success() {
        return SUCCESS;
    }

    /**
     * 失败
     *
     * @return
     */
    public static String failure() {
        return FAILURE;
    }
}
