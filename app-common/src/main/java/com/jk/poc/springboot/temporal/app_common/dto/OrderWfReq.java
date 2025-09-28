package com.jk.poc.springboot.temporal.app_common.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderWfReq {

    private String orderId;
    private String customerId;
    private List<OrderLineReq> orderLineReqs;
}
