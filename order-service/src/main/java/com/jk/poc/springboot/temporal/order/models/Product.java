package com.jk.poc.springboot.temporal.order.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product {
    @Id
    @Column(name = "product_id")
    private String productId;

    @Column(name = "product_code")
    private String productCode;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;
}
