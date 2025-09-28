package com.jk.poc.springboot.temporal.app_common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
public class OrderWfRespMaster implements Serializable {
    private String orderId;
    private OrderWfResp orderWfResp;
    private PaymentWfResp paymentWfResp;
    private ShippingWfResp shippingWfResp;
}
