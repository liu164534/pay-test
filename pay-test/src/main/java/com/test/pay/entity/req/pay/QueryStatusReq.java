package com.test.pay.entity.req.pay;

import com.test.pay.entity.req.BasePayRequest;
import lombok.Data;
import lombok.ToString;

/**
 * @program: pay-test
 * @description: 支付状态查询Req
 * @author: Liu Xinpeng
 **/
@Data
@ToString(callSuper = true)
public class QueryStatusReq extends BasePayRequest {


    /**
     * 订单编号
     */
    private String flowNo;
}