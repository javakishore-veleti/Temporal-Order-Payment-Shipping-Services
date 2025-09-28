package com.jk.poc.springboot.temporal.app_common.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OrderLineReq {
    private String orderId;
    private String orderLineId;
    private Integer lineNo;
    private String productId;
    private String productName;
    private Integer quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal discountPrice;
    private BigDecimal taxPrice;
    private BigDecimal discountTaxPrice;
    private BigDecimal totalTaxPrice;
    private BigDecimal discountTax;
    private BigDecimal totalDiscount;
    private BigDecimal taxDiscount;
}
