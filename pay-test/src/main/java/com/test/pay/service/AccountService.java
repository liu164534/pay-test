package com.test.pay.service;
import com.test.pay.response.ResponseBody;


/**
 * @author Liu
 */
public interface AccountService {

    /**
     * 查询账户信息
     * @return
     * @param merchantNo
     * @param terminalNo
     */
    ResponseBody getAccount(String merchantNo, String terminalNo);
}
