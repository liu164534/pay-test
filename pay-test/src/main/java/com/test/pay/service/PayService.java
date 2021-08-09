package com.test.pay.service;

import com.test.pay.entity.req.pay.PayReq;
import com.test.pay.entity.req.pay.QueryStatusReq;
import com.test.pay.response.ResponseBody;

/**
 * @program: pay-test
 * @description: 支付
 * @author: Liu Xinpeng
 * @create: 2021-08-02 15:45
 **/
public interface PayService {

    /**
     * 查询支付状态
     * @param req
     * @return
     */
    ResponseBody queryStatus(QueryStatusReq req);

    /**
     * 支付提交
     * @param req
     * @return
     */
    ResponseBody paySubmit(PayReq req);

    /**
     * 撤销支付
     * @param req
     * @return
     */
    ResponseBody cancel(PayReq req);

    /**
     * 退款查询
     * @param req
     * @return
     */
    ResponseBody refundQuery(QueryStatusReq req);

    /**
     * 退款
     * @param req
     * @return
     */
    ResponseBody refund(PayReq req);
}