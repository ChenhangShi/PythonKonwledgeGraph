package com.group2.controller;

import com.group2.entity.CommonResult;
import com.group2.entity.Payment;
import com.group2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping(value = "/provider/payment/create")
    public CommonResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果" + result);
        if (result > 0) {
            return new CommonResult(200, "插入成功", result);
        } else {
            return new CommonResult(500, "插入失败");
        }
    }

    @GetMapping(value = "provider/payment/get/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("****查询结果" + payment);
        if (payment != null) {
            return new CommonResult(200, "查询成功", payment);
        } else {
            return new CommonResult(500, "没有该记录，查询失败");
        }
    }
}
