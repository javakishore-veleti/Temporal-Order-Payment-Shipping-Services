package com.jk.poc.springboot.temporal.order.controllers;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfRespMaster;
import com.jk.poc.springboot.temporal.order.facades.OrdersWfFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/temporal/v1/orders")
public class OrdersController {

    @Autowired
    private OrdersWfFacade ordersWfFacade;

    @RequestMapping(method = RequestMethod.POST)
    public OrderWfRespMaster createOrder(@RequestBody OrderWfReq orderWfReq) {
        OrderWfRespMaster orderWfRespMaster = new OrderWfRespMaster();

        ordersWfFacade.createOrder(orderWfRespMaster, orderWfReq);

        return orderWfRespMaster;
    }
}
