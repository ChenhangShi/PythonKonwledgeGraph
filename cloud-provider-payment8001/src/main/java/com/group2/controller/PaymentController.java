package com.group2.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import com.group2.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author YangPx
 * @create 2021-09-25 11:36
 */
@RestController
@Slf4j
@RefreshScope
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configinfo;

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

    @PostMapping(value = "/payment")
    public CommonResult<Integer> insertPayment(@RequestBody Payment payment) {
        boolean b = paymentService.save(payment);
        log.info("****插入成功" + b);
        if (b) {
            return new CommonResult<>(200, "插入成功");
        } else {
            return new CommonResult<>(417, "插入失败");
        }
    }

    @DeleteMapping(value = "/payment/{id}")
    public CommonResult<Payment> deletePayment(@PathVariable Long id) {
        boolean b = paymentService.removeById(id);
        if (b) {
            return new CommonResult<>(200, "删除成功");
        } else {
            return new CommonResult<>(410, "没有该记录，查询失败");
        }
    }

    @PutMapping(value = "/payment")
    public CommonResult<Payment> updatePayment(@RequestBody Payment payment) {
        boolean b = paymentService.updateById(payment);
        if (b) {
            return new CommonResult<>(200, "更新成功");
        } else {
            return new CommonResult<>(410, "没有该记录，查询失败");
        }
    }

    @GetMapping(value = "/payment")
    public CommonResult getAllPayments() {
        List<Payment> payments = paymentService.list();
        log.info("****查询结果" + payments);
        if (payments != null) {
            return new CommonResult(200, "查询成功from application: 8001", payments);
        } else {
            return new CommonResult(410, "没有该记录，查询失败from application: 8001");
        }
    }

    @GetMapping("/configinfo")
    public CommonResult getConfiginfo() {
        return new CommonResult(200, "config info from application: 8001", configinfo);
    }

    @GetMapping(value = "/payment/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment byId = paymentService.getById(id);
        log.info("****查询结果" + byId);
        if (byId != null) {
            return new CommonResult<>(200, "查询成功from application: 8001", byId);
        } else {
            return new CommonResult<>(410, "没有该记录，查询失败from application: 8001");
        }
    }


    @GetMapping(value = "/table")
    public CommonResult getTable(@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
        int pageSize = 2;
        Page<Payment> paymentPage = new Page<>(pn, pageSize);
        Page<Payment> page = paymentService.page(paymentPage, null);

        log.info("****查询结果" + page);
        if (page != null) {
            return new CommonResult<>(200, "查询成功", page.getRecords());
        } else {
            return new CommonResult<>(410, "没有数据");
        }
    }
}
