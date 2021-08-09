package com.test.pay;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Liu
 */
@MapperScan(value = "com.test.pay.dao.*")
@SpringBootApplication
public class TransferApplication {
    public static void main(String[] args) {
        new SpringApplication(TransferApplication.class).run(args);
    }
}
