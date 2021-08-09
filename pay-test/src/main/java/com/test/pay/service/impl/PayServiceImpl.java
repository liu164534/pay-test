package com.test.pay.service.impl;

import com.test.pay.constant.Constants;
import com.test.pay.dao.AccountDao;
import com.test.pay.dao.PayDao;
import com.test.pay.entity.req.pay.PayReq;
import com.test.pay.entity.req.pay.QueryStatusReq;
import com.test.pay.entity.vo.PayRefundVo;
import com.test.pay.entity.vo.PayStatusVo;
import com.test.pay.enums.Status;
import com.test.pay.response.ResponseBody;
import com.test.pay.service.PayService;
import com.test.pay.utils.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;


/**
 * @program: pay-test
 * @description:
 * @author: Liu Xinpeng
 **/
@Slf4j
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayDao payDao;

    @Autowired
    private AccountDao accountDao;



    @Override
    public ResponseBody queryStatus(QueryStatusReq req) {
        ResponseBody response = new ResponseBody();
        response.setSuccess(true);
        try {
            PayStatusVo payStatus = payDao.queryStatus(req);
            // 没有查到支付订单，直接返回
            if (null == payStatus) {
                response.setCode(Status.FAILED.getCode());
                response.setMessage(Constants.PAY_ORDER_NOT_EXISTENCE + req.getFlowNo());
                return response;
            }
            // 转换支付订单的状态
            payStatus.setPayStatus(trPayStatus(payStatus.getPayStatus()));
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(Status.SUCCESS.getValue());
            response.setData(payStatus);
            log.info(String.valueOf(response));
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setSuccess(false);
            response.setCode(Status.FAILED.getCode());
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
    }

    @Override
    public ResponseBody paySubmit(PayReq req) {
        ResponseBody response = new ResponseBody();
        response.setSuccess(false);
        response.setCode(Status.FAILED.getCode());

        try {
            // 判断是否为重复充值操作，存在订单直接返回
            Integer count = payOrderCount(req.getMerchantNo(), req.getTerminalNo(), req.getFlowNo());
            if (0 != count) {
                response.setMessage(Constants.PAY_PROCESSING_OR_SUCCESS + req.getFlowNo());
                return response;
            }
            // 将这笔订单插入到支付订单流水表，并更新对应商户终端的金额
            Integer state = RandomUtils.randomStatus();
            int i = payDao.addPayOrder(req,state);
            if (0 == i) {
                response.setCode(Status.SUCCESS.getCode());
                response.setMessage(Constants.PAY_FAILED);
                return response;
            }
            if (1 == state) {
                // 修改账户金额
                accountDao.updateAccountAmount(req);
            }
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(trPayStatus(String.valueOf(state)));
            response.setData(req);
            return response;

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setSuccess(false);
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
    }

    @Override
    public ResponseBody cancel(PayReq req) {
        ResponseBody response = new ResponseBody();
        response.setSuccess(true);
        response.setCode(Status.FAILED.getCode());
        try {
            // 没有查到对应的支付订单直接返回
            Integer count = payOrderCount(req.getMerchantNo(), req.getTerminalNo(), req.getFlowNo());
            if (0 == count) {
                response.setMessage(Constants.PAY_ORDER_NOT_EXISTENCE + req.getFlowNo());
                return response;
            }
            Integer cancelCount = cancelRefundCount(req.getMerchantNo(), req.getTerminalNo(), req.getFlowNo());
            // 已经存在非撤销失败的记录直接返回
            if (0 != cancelCount) {
                response.setCode(Status.SUCCESS.getCode());
                response.setMessage(Constants.CANCEL_PROCESSING_OR_SUCCESS + req.getFlowNo());
                return response;
            }
            // 撤销支付 将这笔订单插入到撤销/退款订单流水表，并更新对应商户终端的金额
            Integer state = RandomUtils.randomStatus();
            req.setAmount(req.getAmount().negate());

            int i = payDao.addCancelRefundOrder(req,state);
            if (0 == i) {
                response.setCode(Status.FAILED.getCode());
                response.setMessage(Constants.CANCEL_FAILED);
                return response;
            }
            if (1 == state) {
                // 扣减对应金额
                accountDao.updateAccountAmount(req);
            }
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(trCancelStatus(String.valueOf(state)));
            response.setData(req);
            return response;
        } catch (DuplicateKeyException e) {
            log.error(e.getMessage(), e);
            response.setSuccess(false);
            response.setMessage(Constants.CANCEL_ORDER_EXISTENCE + req.getFlowNo());
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setSuccess(false);
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
    }

    @Override
    public ResponseBody refundQuery(QueryStatusReq req) {
        ResponseBody response = new ResponseBody();
        try {
            PayRefundVo payRefund = payDao.queryRefund(req);
            // 没有查到退款订单，直接返回
            if (null == payRefund) {
                response.setCode(Status.FAILED.getCode());
                response.setMessage(Constants.REFUND_ORDER_NOT_EXISTENCE + req.getFlowNo());
                return response;
            }
            // 转换退款订单的状态
            payRefund.setRefundStatus(trRefundStatus(payRefund.getRefundStatus()));
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(Status.SUCCESS.getValue());
            response.setData(payRefund);
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setCode(Status.FAILED.getCode());
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
    }

    @Override
    public ResponseBody refund(PayReq req) {
        ResponseBody response = new ResponseBody();
        response.setCode(Status.FAILED.getCode());

        try {
            Integer cancelCount = cancelRefundCount(req.getMerchantNo(), req.getTerminalNo(), req.getFlowNo());
            // 已经存在非退款失败的记录直接返回
            if (0 != cancelCount) {
                response.setMessage(Constants.REFUND_PROCESSING_OR_SUCCESS);
                return response;
            }
            // 退款 将这笔退款单插入到撤销/退款订单流水表，并更新对应商户终端的金额
            Integer state = RandomUtils.randomStatus();
            req.setAmount(req.getAmount().negate());

            int i = payDao.addCancelRefundOrder(req,state);
            if (0 == i) {
                response.setCode(Status.FAILED.getCode());
                response.setMessage(Constants.REFUND_FAILED);
                return response;
            }
            if (1 == state) {
                // 扣减对应金额
                accountDao.updateAccountAmount(req);
            }
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(trRefundStatus(String.valueOf(state)));
            response.setData(req);
            return response;

        } catch (DuplicateKeyException e) {
            log.error(e.getMessage(), e);
            response.setMessage(Constants.REFUND_ORDER_EXISTENCE + req.getFlowNo());
            return response;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
    }


    /**
     * 撤销、退款场景
     * 统计状态为成功或者是处理中的订单数量
     * @param merchantNo
     * @param terminalNo
     * @param flowNo
     * @return
     */
    private Integer cancelRefundCount(String merchantNo, String terminalNo, String flowNo) {
        Integer count = payDao.queryCancelRefundCount(merchantNo, terminalNo, flowNo);
        return count;
    }

    /**
     * 支付场景
     * 判断支付订单是否存在
     * @return
     */
    private Integer payOrderCount(String merchantNo, String terminalNo, String flowNo) {
        Integer count = payDao.queryOrderCount(merchantNo, terminalNo, flowNo);
        return count;
    }

    /**
     * 转换支付单的状态
     * @return
     */
    private String trPayStatus(String status) {
        if (null == status || "".equals(status)){
            return Constants.PAY_PROCESSING;
        }
        if (status.equals(Constants.ZERO)) {
            return Constants.PAY_PROCESSING;
        } else {
            return Integer.valueOf(status) == 1 ? Constants.PAY_SUCCESS :Constants.PAY_FAILED;
        }
    }

    /**
     * 转换撤销单的状态
     * @return
     */
    private String trCancelStatus(String status) {
        if (null == status || "".equals(status)){
            return Constants.CANCEL_PROCESSING;
        }
        if (status.equals(Constants.ZERO)) {
            return Constants.CANCEL_PROCESSING;
        } else {
            return Integer.valueOf(status) == 1 ? Constants.CANCEL_SUCCESS :Constants.CANCEL_FAILED;
        }
    }


    /**
     * 转换退款单的状态
     * @return
     */
    private String trRefundStatus(String status) {
        if (null == status || "".equals(status)){
            return Constants.REFUND_PROCESSING;
        }
        if (status.equals(Constants.ZERO)) {
            return Constants.REFUND_PROCESSING;
        } else {
            return Integer.valueOf(status) == 1 ? Constants.REFUND_SUCCESS : Constants.REFUND_FAILED;
        }
    }


}