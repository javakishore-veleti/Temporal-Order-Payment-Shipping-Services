package com.jk.poc.springboot.temporal.app_common.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
@Setter
@Getter
@NoArgsConstructor
public class OrderWfResp implements Serializable {

    public OrderWfResp(String orderId) {
        this.orderId = orderId;
    }

    private String orderId;
    private String businessProcessId;
}
