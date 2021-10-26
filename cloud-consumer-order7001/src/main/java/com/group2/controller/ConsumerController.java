package com.group2.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import com.group2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author YangPx
 * @create 2021-09-25 19:33
 */
@RestController
@Slf4j
@RefreshScope
public class ConsumerController {

    @Value("${service-url.nacos-provider-service}")
    private String providerUrl;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private PaymentService paymentService;

    @PostMapping("/payment")
    public CommonResult<Payment> create(Payment payment) {
        return paymentService.insert(payment);
//        return restTemplate.postForObject(providerUrl + "/payment/create", payment, CommonResult.class);
    }

    @GetMapping("/payment")
    public CommonResult getAllPayment() {
        log.info("进入");
        return paymentService.getAllPayment();
//        return restTemplate.getForObject(providerUrl + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentService.getPaymentById(id);
//        return restTemplate.getForObject(providerUrl + "/payment/get/" + id, CommonResult.class);
    }

    @GetMapping("/configinfo")
    public CommonResult getConfiginfo() {
        return new CommonResult(200, "config info", paymentService.getConfiginfo());
    }

    @RequestMapping("/consumer/fallback/{id}")
    //@SentinelResource(value = "fallback") //没有配置
    //@SentinelResource(value = "fallback",fallback = "handlerFallback") //fallback只负责业务异常
    //@SentinelResource(value = "fallback",blockHandler = "blockHandler") //blockHandler只负责sentinel控制台配置违规
    @SentinelResource(value = "fallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public CommonResult<Payment> fallback(@PathVariable Long id) {
        CommonResult<Payment> result = paymentService.getPaymentById(id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常....");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录,空指针异常");
        }
        return result;
    }

    //本例是fallback
    public CommonResult<Payment> handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(444, "兜底异常handlerFallback,exception内容  " + e.getMessage(), payment);
    }

    //本例是blockHandler
    public CommonResult<Payment> blockHandler(@PathVariable Long id, BlockException blockException) {
        Payment payment = new Payment(id, "null");
        return new CommonResult<>(445, "blockHandler-sentinel限流,无此流水: blockException  " + blockException.getMessage(), payment);
    }
}
