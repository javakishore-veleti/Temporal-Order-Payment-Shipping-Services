package com.jk.poc.springboot.temporal.app_common.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;

@JsonAutoDetect(fieldVisibility = ANY)
@Getter
@Setter
@NoArgsConstructor
public class ShippingWfResp implements Serializable {

    private String shipmentId;
    private String businessProcessId;

    public ShippingWfResp(String shipmentId) {
        this.shipmentId = shipmentId;
    }

}
