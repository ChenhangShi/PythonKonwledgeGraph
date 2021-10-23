package com.group2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.group2.entities.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YangPx
 * @create 2021-10-22 21:27
 * @tips: 继承BaseMapper，即可crud，不用再写xml文件
 */
@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
