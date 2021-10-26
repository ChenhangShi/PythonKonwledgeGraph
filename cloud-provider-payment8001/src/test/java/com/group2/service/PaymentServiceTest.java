package com.group2.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author YangPx
 * @create 2021-10-24 15:22
 */
@SpringBootTest
@Slf4j
class PaymentServiceTest {
    @Autowired
    PaymentService paymentService;

    @Test
    public void test1() {
        int pageSize = 2;
        Page<Payment> paymentPage = new Page<>(1, pageSize);
        Page<Payment> page = paymentService.page(paymentPage, null);

        log.info("****查询结果" + page);
        log.info("****查询结果" + page.getRecords());
        log.info("****查询结果" + page.getCurrent());
        log.info("****查询结果" + page.getSize());
        assertEquals(4, page.getSize());
    }

    public void test2() {
        paymentService.removeById(1);
    }
}