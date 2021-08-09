package com.test.pay.controller;

import com.test.pay.entity.req.pay.PayReq;
import com.test.pay.entity.req.pay.QueryStatusReq;
import com.test.pay.response.ResponseBody;
import com.test.pay.service.PayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: pay-test
 * @description: 支付相关接口
 * @author: Liu Xinpeng
 **/
@Slf4j
@RestController
@RequestMapping(value = "/pay")
public class PayController {

    @Autowired
    private PayService service;

    /**
     * 支付
     * @param req
     * @return
     */
    @RequestMapping(value = "/submit", method = RequestMethod.PUT)
    ResponseBody submit(@RequestBody PayReq req) {
        log.info("PayController submit req:{}", req);
        return service.paySubmit(req);
    }

    /**
     * 支付查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/queryStatus", method = RequestMethod.POST)
    ResponseBody queryStatus(@RequestBody QueryStatusReq req) {
        log.info("PayController queryStatus req:{}", req);
        return service.queryStatus(req);
    }

    /**
     * 撤销支付
     * @param req
     * @return
     */
    @RequestMapping(value = "/cancel", method = RequestMethod.PUT)
    ResponseBody cancel(@RequestBody PayReq req) {
        log.info("PayController cancel req:{}", req);
        return service.cancel(req);
    }



    /**
     * 退款查询
     * @param req
     * @return
     */
    @RequestMapping(value = "/refundQuery", method = RequestMethod.POST)
    ResponseBody refundQuery(@RequestBody QueryStatusReq req) {
        log.info("PayController refundQuery req:{}", req);
        return service.refundQuery(req);
    }

    /**
     * 退款
     * @param req
     * @return
     */
    @RequestMapping(value = "/refund", method = RequestMethod.PUT)
    ResponseBody refund(@RequestBody PayReq req) {
        log.info("PayController refund req:{}", req);
        return service.refund(req);
    }






}