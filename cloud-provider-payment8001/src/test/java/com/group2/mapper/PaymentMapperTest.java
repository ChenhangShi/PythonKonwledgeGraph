package com.group2.mapper;

import com.group2.entities.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author YangPx
 * @create 2021-10-22 21:38
 */
@SpringBootTest
@Slf4j
public class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;

    @Test
    public void testSelect() {
        Payment payment = paymentMapper.selectById(31);
        log.info(payment.toString());
//        System.out.println(("----- selectAll method test ------"));
//        List<Payment> userList = paymentMapper.selectList(null);
//        userList.forEach(System.out::println);
    }

//    @Test
//    public void testRedis() {
//        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
//        operations.set("hello", "world");
//        log.info(operations.get("hello"));
//    }

}