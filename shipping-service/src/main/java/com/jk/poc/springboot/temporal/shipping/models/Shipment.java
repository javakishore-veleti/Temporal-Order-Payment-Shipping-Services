package com.jk.poc.springboot.temporal.shipping.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Shipment {

    @Id
    @Column(name = "shipment_id")
    private String shipmentId;

    @Column(name = "prder_id")
    private String orderId;

    @Column(name = "pament_id")
    private String paymentId;

    @Column(name = "status")
    private String status;

    @Column(name = "created_dt")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}