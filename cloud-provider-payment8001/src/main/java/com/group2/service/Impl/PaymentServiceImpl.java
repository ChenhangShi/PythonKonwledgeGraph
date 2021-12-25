package com.group2.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.group2.entities.Payment;
import com.group2.mapper.PaymentMapper;
import com.group2.service.PaymentService;
import org.springframework.stereotype.Service;


/**
 * @author YangPx
 * @create 2021-09-25 11:33
 */
@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, Payment> implements PaymentService {
}
