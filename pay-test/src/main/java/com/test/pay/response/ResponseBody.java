package com.test.pay.response;

import com.test.pay.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一返回结果
 * @author Liu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBody<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * true表示业务处理成功,false表示业务处理失败
     */
    private int code;

    /**
     * 返回的对象类型
     */
    private T data;

    /**
     * 业务处理返回信息
     */
    private String message;

    /**
     * 是否成功
     * @param code
     * @param result
     */
    private boolean success;

    public ResponseBody(int code ,T result) {
        this.code = code;
        this.data = result;
    }

    public ResponseBody(RuntimeException e) {
        this.code = Status.FAILED.getCode();
        this.message = e.getMessage();
    }
}
