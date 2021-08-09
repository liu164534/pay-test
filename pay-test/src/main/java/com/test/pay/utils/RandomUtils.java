package com.test.pay.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @program: pay-test
 * @description: 随机数工具
 * @author: Liu Xinpeng
 * @create: 2021-08-03 10:26
 **/
public class RandomUtils {

    /**
     * 生成随机的支付、撤销、退款状态
     * @return
     */
    public static Integer randomStatus() {
        List<Integer> status = new ArrayList<>();
        for  (int i = -1; i <= 1; i++) {
            status.add(-1);
            status.add(0);
            status.add(1);
            status.add(-1);
            status.add(0);
            status.add(1);
        }

        Random rm = new Random();
        Integer i = status.get(rm.nextInt(10));
        return i;
    }
}