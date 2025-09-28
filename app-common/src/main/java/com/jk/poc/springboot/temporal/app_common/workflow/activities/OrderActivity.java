package com.jk.poc.springboot.temporal.app_common.workflow.activities;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfResp;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface OrderActivity {

    @ActivityMethod
    OrderWfResp placeOrder(OrderWfReq orderWfReq);

    @ActivityMethod
    OrderWfResp completeOrder(OrderWfReq orderWfReq);

    @ActivityMethod
    OrderWfResp refundOrder(OrderWfReq orderWfReq);

    @ActivityMethod
    OrderWfResp getOrderInfo(OrderWfReq orderWfReq);

    @ActivityMethod
    OrderWfResp cancelOrder(OrderWfResp orderWfResp);
}
