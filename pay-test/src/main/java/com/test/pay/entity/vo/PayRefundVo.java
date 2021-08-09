package com.test.pay.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @program: pay-test
 * @description: 退款结果
 * @author: Liu Xinpeng
 * @create: 2021-08-03 15:21
 **/
@Data
public class PayRefundVo implements Serializable {

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 终端号
     */
    private String terminalNo;

    /**
     * 退款流水号
     */
    private String flowNo;

    /**
     * 退款金额
     */
    private BigDecimal refundAmount;


    /**
     * 支付状态
     * 1 ：支付成功
     * 0 ：支付处理中
     * -1 ：支付失败
     */
    private String refundStatus;
}