package com.sky.service;

import com.sky.entity.Orders;
import com.sky.mapper.mapper.OrderMapper;
import com.sky.mapper.mapper.UserMapper;
import com.sky.vo.BusinessDataVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;



@Service
public class WorkspaceService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private UserMapper userMapper;

    public BusinessDataVO getBusinessData(LocalDateTime begin, LocalDateTime end) {
        // 查询营业额（假设是所有已完成订单金额总和）
        Double turnover = orderMapper.sumTurnover(begin, end);

        // 查询订单总数 & 有效订单数
        Integer totalOrderCount = orderMapper.countOrders(begin, end, null);
        Integer validOrderCount = orderMapper.countOrders(begin, end, Orders.COMPLETED);

        // 计算订单完成率
        Double orderCompletionRate = 0.0;
        if (totalOrderCount != null && totalOrderCount != 0) {
            orderCompletionRate = validOrderCount * 1.0 / totalOrderCount;
        }

        // 计算客单价
        Double unitPrice = 0.0;
        if (validOrderCount != null && validOrderCount != 0) {
            unitPrice = turnover / validOrderCount;
        }

        // 查询新增用户数
        Integer newUsers = userMapper.countNewUsers(begin, end);

        // 封装结果
        return BusinessDataVO.builder()
                .turnover(turnover)
                .validOrderCount(validOrderCount)
                .orderCompletionRate(orderCompletionRate)
                .unitPrice(unitPrice)
                .newUsers(newUsers)
                .build();
    }
}
