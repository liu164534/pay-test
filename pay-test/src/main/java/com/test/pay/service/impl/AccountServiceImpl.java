package com.test.pay.service.impl;

import com.test.pay.dao.AccountDao;
import com.test.pay.enums.Status;
import com.test.pay.entity.vo.AccountVo;
import com.test.pay.response.ResponseBody;
import com.test.pay.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Liu
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;



    /**
     * 查询所有账户信息
     * @return
     * @param merchantNo
     * @param terminalNo
     */
    @Override
    public ResponseBody getAccount(String merchantNo, String terminalNo) {
        ResponseBody response = new ResponseBody();

        if ("".equals(merchantNo) || merchantNo == null) {
            response.setCode(Status.FAILED.getCode());
            response.setMessage(Status.FAILED.getValue());
            return response;
        }
        try {
            AccountVo account = accountDao.getAccount(merchantNo, terminalNo);
            response.setCode(Status.SUCCESS.getCode());
            response.setMessage(Status.SUCCESS.getValue());
            response.setData(account);
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
            response.setCode(Status.FAILED.getCode());
            response.setMessage(Status.FAILED.getValue());
            return response;
        }

    }
}
