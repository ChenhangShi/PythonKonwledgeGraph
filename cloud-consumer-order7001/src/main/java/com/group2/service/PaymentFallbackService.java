package com.group2.service;

import com.group2.entities.CommonResult;
import com.group2.entities.Payment;
import org.springframework.stereotype.Component;

/**
 * @author YangPx
 * @create 2021-09-26 09:18
 * @description 作为openfeign调用的兜底
 */
@Component
public class PaymentFallbackService implements PaymentService {

    @Override
    public CommonResult insert(Payment payment) {
        return new CommonResult(444, "兜底from consumer /payment");
    }

    @Override
    public CommonResult getPaymentById(Long id) {
        return new CommonResult(444, "兜底from consumer /payment/{id}");
    }

    @Override
    public CommonResult getAllPayment() {
        return new CommonResult(444, "兜底from consumer /payment");
    }
}
