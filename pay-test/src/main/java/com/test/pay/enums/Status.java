package com.test.pay.enums;

/**
 * @author Liu
 */
public enum Status {

    PROCESSING(0, "正在处理中..."),
    SUCCESS(1, "操作成功"),
    FAILED(2, "操作失败");

    private final int code;
    private final String value;

    Status(int code, String value) {
        this.value = value;
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public int getCode() {
        return code;
    }
}
