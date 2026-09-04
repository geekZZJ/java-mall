package org.example.mall.mapper;

import org.example.mall.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order row);

    int insertSelective(Order row);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order row);

    int updateByPrimaryKey(Order row);
}