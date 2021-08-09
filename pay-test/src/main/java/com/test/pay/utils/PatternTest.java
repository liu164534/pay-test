package com.test.pay.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: pay-test
 * @description:
 * @author: Liu Xinpeng
 * @create: 2021-08-05 14:58
 **/
public class PatternTest {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("^88.");
        Matcher matcher = pattern.matcher("88wewe");
        if (matcher.find()) {
            System.out.println(123);
        }
    }
}