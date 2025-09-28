package com.jk.poc.springboot.temporal.app_common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderWfRespMaster {
    private String orderId;
    private OrderWfResp orderWfResp;
    private PaymentWfResp paymentWfResp;
    private ShippingWfResp shippingWfResp;
}
