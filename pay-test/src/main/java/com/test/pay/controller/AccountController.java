package com.test.pay.controller;

import com.test.pay.response.ResponseBody;
import com.test.pay.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询账户信息的入口
 * @author Liu
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
public class AccountController {

    @Autowired
    private AccountService accountService;


    /**
     * 获取账户信息
     * @param merchantNo
     * @param terminalNo
     * @return
     */
    @RequestMapping(value = "/account", method = RequestMethod.GET)
    ResponseBody getAccount(@RequestParam String merchantNo,
                            @RequestParam String terminalNo) {
        log.info("getAccount merchantNo:{} terminalNo:{}", merchantNo, terminalNo);
        return accountService.getAccount(merchantNo, terminalNo);
    }
}
