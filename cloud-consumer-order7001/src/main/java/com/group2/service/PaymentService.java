package com.group2.service;

import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author YangPx
 * @create 2021-09-26 09:13
 * @description openfeign调用另一个服务
 */
@FeignClient(value = "${service-url.nacos-provider-service}", fallback = PaymentFallbackService.class)
public interface PaymentService {

    @PostMapping(value = "/payment")
    CommonResult<Payment> insert(@RequestBody Payment payment);

    @GetMapping(value = "/payment/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment")
    CommonResult getAllPayment();

    @GetMapping(value = "/configinfo")
    CommonResult getConfiginfo();
}
