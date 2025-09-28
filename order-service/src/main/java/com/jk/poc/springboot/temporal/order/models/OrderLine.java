package com.jk.poc.springboot.temporal.order.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name = "order_lines")
public class OrderLine {

    @Id
    @Column(name = "order_line_id")
    private String orderLineId;

    @Column(name = "line_no")
    private Integer lineNo;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "qty")
    private Integer quantity;

    @Column(name = "unit_price", precision = 10, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "total_price", precision = 10, scale = 2)
    private BigDecimal totalPrice;

    @Column(name = "discount_price", precision = 10, scale = 2)
    private BigDecimal discountPrice;

    @Column(name = "tax_price", precision = 10, scale = 2)
    private BigDecimal taxPrice;

    @Column(name = "discount_tax_price", precision = 10, scale = 2)
    private BigDecimal discountTaxPrice;

    @Column(name = "total_tax_price", precision = 10, scale = 2)
    private BigDecimal totalTaxPrice;

    @Column(name = "discount_tax", precision = 10, scale = 2)
    private BigDecimal discountTax;

    @Column(name = "total_discount", precision = 10, scale = 2)
    private BigDecimal totalDiscount;

    @Column(name = "tax_discount", precision = 10, scale = 2)
    private BigDecimal taxDiscount;

    // Foreign key column in OrderLine table
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
}
