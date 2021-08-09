package com.test.pay.utils;

import javax.sound.midi.Soundbank;
import javax.swing.*;
import java.util.UUID;

/**
 * @program: pay-test
 * @description:
 * @author: Liu Xinpeng
 * @create: 2021-08-05 14:02
 **/
public class Uuid {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        System.out.println(String.valueOf(uuid).replace("-", ""));
    }
}