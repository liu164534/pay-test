package com.test.pay.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 账户实体
 * @author Liu
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountVo implements Serializable {


    /**
     * 数据库自增id
     * */
    private Long id;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 终端号
     */
    private String terminalNo;

    /**
     * 可用金额
     */
    private BigDecimal amount;

    /**
     * 账户创建时间
     * */
    private Long createTime;



}