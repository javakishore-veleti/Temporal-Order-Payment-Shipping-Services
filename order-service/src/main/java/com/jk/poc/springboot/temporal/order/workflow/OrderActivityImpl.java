package com.jk.poc.springboot.temporal.order.workflow;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfResp;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.OrderActivity;
import com.jk.poc.springboot.temporal.order.models.Order;
import com.jk.poc.springboot.temporal.order.repositories.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class OrderActivityImpl implements OrderActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderActivityImpl.class);

    private OrderRepository orderRepository;

    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderWfResp placeOrder(OrderWfReq orderWfReq) {
        var order = new Order();
        order.setCustomerId(UUID.randomUUID().toString());
        order.setStatus("PLACED");


        var savedOrder = orderRepository.save(order);
        LOGGER.info("Order {} placed for customer {}", savedOrder.getOrderId(), savedOrder.getCustomerId());
        return new OrderWfResp();
    }

    @Override
    public OrderWfResp completeOrder(OrderWfReq orderWfReq) {
        return null;
    }

    @Override
    public OrderWfResp refundOrder(OrderWfReq orderWfReq) {
        return null;
    }

    @Override
    public OrderWfResp getOrderInfo(OrderWfReq orderWfReq) {
        return null;
    }

    @Override
    public OrderWfResp cancelOrder(OrderWfResp orderWfResp) {
        return null;
    }
}
