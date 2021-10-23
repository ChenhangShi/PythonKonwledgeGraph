package com.group2.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import com.group2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author YangPx
 * @create 2021-09-25 11:36
 */
@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${spring.application.name}")
    private String springApplicationName;

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
        return "from application: 8001" + ", id: " + id;
    }

    @PostMapping(value = "/payment")
    public CommonResult<Integer> create(@RequestBody Payment payment) {
        boolean b = paymentService.save(payment);
        log.info("****插入成功" + b);
        if (b) {
            return new CommonResult<>(200, "插入成功");
        } else {
            return new CommonResult<>(417, "插入失败");
        }
    }

    @GetMapping(value = "/payment")
    public CommonResult<Payment> getPaymentById(@RequestParam("id") Long id) {
        Payment byId = paymentService.getById(id);
        log.info("****查询结果" + byId);
        if (byId != null) {
            return new CommonResult<>(200, "查询成功", byId);
        } else {
            return new CommonResult<>(410, "没有该记录，查询失败");
        }
    }
}
