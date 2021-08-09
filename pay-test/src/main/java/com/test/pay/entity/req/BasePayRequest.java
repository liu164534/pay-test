package com.test.pay.entity.req;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @program: pay-test
 * @description:
 * @author: Liu Xinpeng
 **/
@Data
public class BasePayRequest implements Serializable {


    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 终端号
     */
    private String terminalNo;

}