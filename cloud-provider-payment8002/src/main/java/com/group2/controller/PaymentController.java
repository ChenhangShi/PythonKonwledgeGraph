package com.group2.controller;

import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;


/**
 * @author YangPx
 * @create 2021-09-25 20:42
 */
@RestController
@Slf4j
@RefreshScope // 支持Nacos的动态刷新
public class PaymentController {

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/config/info")
    public String getConfigInfo() {
        return configInfo;
    }

    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info(Thread.currentThread().getName() + "\t" + "...testB");
        return "------testB";
    }

    @GetMapping(value = "/payment/nacos/{id}")
    public String getPayment(@PathVariable("id") Long id) {
        return "from application: 8002" + ", id: " + id;
    }

    @GetMapping(value = "/payment")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") Long id) {
        return new CommonResult<>(200, "get payment from 8002, id=" + id);
    }
}
