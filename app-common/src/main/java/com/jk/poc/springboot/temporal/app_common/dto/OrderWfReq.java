package com.jk.poc.springboot.temporal.app_common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Setter
@Getter
public class OrderWfReq implements Serializable {

    private String orderId;
    private String customerId;
    private List<OrderLineReq> orderLineReqs;
    private String businessProcessId;
    private String workflowId;
}
