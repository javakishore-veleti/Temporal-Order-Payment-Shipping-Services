package com.jk.poc.springboot.temporal.order.facades;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfRespMaster;

public interface OrdersWfFacade {

    void createOrder(OrderWfRespMaster orderWfRespMaster, OrderWfReq orderWfReq);
}
