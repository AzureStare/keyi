package com.shaoshi.yixue.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author wanglh
 * @date 2022/4/9 13:48
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JResponse<T> {
    /**
     * 响应成功结果
     */
    private static final int SUCCESS = 1;
    /**
     * 响应失败结果
     */
    private static final int FAIL = 0;

    /**
     * 结果：成功or失败
     */
    private int ret = 0;
    /**
     * 响应数据
     */
    private T data;
    /**
     * 错误消息
     */
    private String errorMsg;
    /**
     * 成功消息
     */
    private String successMsg;
    /**
     * 错误码
     */
    private String errorCode;
    /**
     * 时间戳
     */
    //@ApiModelProperty(value = "时间戳")
    private Date timestamp;

    /**
     * 响应成功
     *
     * @return
     */
    public static JResponse<Object> success() {
        JResponse<Object> rep = new JResponse<Object>();
        rep.setRet(SUCCESS);
        rep.setTimestamp(new Date());
        rep.setSuccessMsg("success");
        return rep;
    }

    /**
     * 响应成功
     *
     * @param data 响应数据
     * @return
     */
    public static <T> JResponse<T> success(T data) {
        JResponse<T> rep = new JResponse<>();
        rep.setRet(SUCCESS);
        rep.setTimestamp(new Date());
        rep.setSuccessMsg("success");
        rep.setData(data);
        return rep;
    }

    /**
     * 响应失败
     *
     * @param errorCode 错误码
     * @return
     */
    public static JResponse<Object> fail(String errorCode) {
        JResponse<Object> rep = new JResponse<>();
        rep.setRet(FAIL);
        rep.setTimestamp(new Date());
        rep.setErrorCode(errorCode);
        return rep;
    }

    /**
     * 响应失败
     *
     * @param errorMsg  错误消息
     * @param errorCode 错误码
     * @return
     */
    public static JResponse<Object> fail(String errorMsg, String errorCode) {
        JResponse<Object> rep = new JResponse<>();
        rep.setRet(FAIL);
        rep.setTimestamp(new Date());
        rep.setErrorMsg(errorMsg);
        rep.setErrorCode(errorCode);
        return rep;
    }

    /**
     * 响应失败
     *
     * @param errorMsg  错误消息
     * @param errorCode 错误码
     * @return
     */
    public static <T> JResponse<T> fail(String errorMsg, String errorCode, T data) {
        JResponse<T> rep = new JResponse<>();
        rep.setRet(FAIL);
        rep.setTimestamp(new Date());
        rep.setErrorMsg(errorMsg);
        rep.setErrorCode(errorCode);
        rep.setData(data);
        return rep;
    }
}
