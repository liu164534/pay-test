package com.test.pay.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: pay-test
 * @description: 支付结果
 * @author: Liu Xinpeng
 * @create: 2021-08-02 17:18
 **/
@Data
public class PayStatusVo implements Serializable {

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 终端号
     */
    private String terminalNo;

    /**
     * 支付流水号
     */
    private String flowNo;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 支付码
     */
    private String payCode;

    /**
     * 退款状态
     * 1 ：退款成功
     * 0 ：退款处理中
     * -1 ：退款失败
     */
    private String payStatus;




}