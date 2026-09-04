package org.example.mall.mapper;

import org.example.mall.entity.Cart;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Cart row);

    int insertSelective(Cart row);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart row);

    int updateByPrimaryKey(Cart row);
}