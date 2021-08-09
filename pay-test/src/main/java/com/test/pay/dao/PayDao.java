package com.test.pay.dao;

import com.test.pay.entity.req.pay.PayReq;
import com.test.pay.entity.req.pay.QueryStatusReq;
import com.test.pay.entity.vo.PayRefundVo;
import com.test.pay.entity.vo.PayStatusVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @program: pay-test
 * @description: 支付相关Dao
 * @author: Liu Xinpeng
 * @create: 2021-08-02 17:15
 **/
@Mapper
public interface PayDao {

    /**
     * 查询支付状态
     * @param req
     * @return
     */
    PayStatusVo queryStatus(QueryStatusReq req);


    /**
     * 查询支付订单是否存在
     * @param merchantNo
     * @param terminalNo
     * @param flowNo
     * @return
     */
    Integer queryOrderCount(@Param("merchantNo") String merchantNo,
                        @Param("terminalNo") String terminalNo,
                        @Param("flowNo") String flowNo);


    /**
     * 插入支付订单
     * @param req
     * @param state
     * @return
     */
    Integer addPayOrder(@Param("pay")PayReq req,
                        @Param("state")Integer state);

    /**
     * 插入撤销/退款订单
     * @param req
     * @param state
     * @return
     */
    Integer addCancelRefundOrder(@Param("cancel")PayReq req,
                                 @Param("state")Integer state);

    /**
     * 查询撤销、退款单数量
     * @param merchantNo
     * @param terminalNo
     * @param flowNo
     * @return
     */
    Integer queryCancelRefundCount(@Param("merchantNo") String merchantNo,
                                   @Param("terminalNo") String terminalNo,
                                   @Param("flowNo") String flowNo);

    /**
     * 退款查询
     * @param req
     * @return
     */
    PayRefundVo queryRefund(QueryStatusReq req);
}












