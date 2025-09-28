package com.jk.poc.springboot.temporal.app_common.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
@Getter
@Setter
public class ShippingWfReq implements Serializable {
    private String shipmentId;
    private String orderId;
    private String paymentId;
    private String businessProcessId;
}
