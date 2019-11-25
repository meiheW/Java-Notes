package com.tomster.mybatis.mapper;

import com.tomster.mybatis.po.OrderExt;
import org.apache.ibatis.annotations.Param;

/**
 * @author meihewang
 * @date 2019/11/25  23:29
 */
public interface OrderMapper {

    public OrderExt findOrderById(@Param("id") int id);
}
