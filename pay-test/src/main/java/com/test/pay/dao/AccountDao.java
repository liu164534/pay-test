package com.test.pay.dao;
import com.test.pay.entity.req.pay.PayReq;
import com.test.pay.entity.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * @author Liu
 */
@Mapper
public interface AccountDao {


    /**
     * 查询账户信息
     * @param merchantNo
     * @param terminalNo
     * @return
     */
    AccountVo getAccount(@Param("merchantNo") String merchantNo,
                         @Param("terminalNo") String terminalNo);

    /**
     * 修改账户金额
     * @param req
     */
    void updateAccountAmount(PayReq req);
}
