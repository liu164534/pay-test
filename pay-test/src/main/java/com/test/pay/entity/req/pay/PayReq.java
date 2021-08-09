package com.test.pay.entity.req.pay;

import com.test.pay.entity.req.BasePayRequest;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.BitSet;

/**
 * @program: pay-test
 * @description: 支付查询参数
 * @author: Liu Xinpeng
 * @create: 2021-08-02 17:06
 **/
@Data
@ToString(callSuper = true)
public class PayReq extends BasePayRequest {

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付条码
     */
    private String payCode;

    /**
     * 支付流水单号
     * 支付和撤销是的单号是匹配的
     * 退款时的单号与支付和撤销时的单号是没有关系的
     */
    private String flowNo;


}