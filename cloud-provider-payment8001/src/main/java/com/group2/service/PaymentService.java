package com.group2.service;

import com.group2.entities.Payment;
import org.apache.ibatis.annotations.Param;

/**
 * @author YangPx
 * @create 2021-09-25 11:32
 */

public interface PaymentService {

    public int create(Payment payment);

    public Payment getPaymentById(@Param("id") Long id);
}
