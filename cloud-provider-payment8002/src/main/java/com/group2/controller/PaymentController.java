package com.group2.controller;

import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author YangPx
 * @create 2021-09-25 20:42
 */
@RestController
@Slf4j
@RefreshScope
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configinfo;

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return new CommonResult<>(200, "get payment from 8002, id=" + id);
    }

    @GetMapping(value = "/payment")
    public CommonResult getAllPayments() {
        return new CommonResult(200, "get all payments from 8002");
    }

    @GetMapping("/configinfo")
    public CommonResult getConfiginfo() {
        return new CommonResult(200, "config info from application: 8002", configinfo);
    }
}
