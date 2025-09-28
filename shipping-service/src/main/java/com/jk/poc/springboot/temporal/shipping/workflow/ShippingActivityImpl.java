package com.jk.poc.springboot.temporal.shipping.workflow;

import com.jk.poc.springboot.temporal.app_common.dto.ShippingWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.ShippingWfResp;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.ShippingActivity;
import com.jk.poc.springboot.temporal.shipping.models.Shipment;
import com.jk.poc.springboot.temporal.shipping.repositories.ShipmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ShippingActivityImpl implements ShippingActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShippingActivityImpl.class);

    @Autowired
    private ShipmentRepository shipmentRepository;

    @Override
    public ShippingWfResp processShipment(ShippingWfReq shippingWfReq) {
        LOGGER.info("STARTED SHIPMENT ACTIVITY Business Process ID {}", shippingWfReq.getBusinessProcessId());

        var shipment = new Shipment();
        shipment.setShipmentId(UUID.randomUUID().toString());
        shipment.setOrderId(shippingWfReq.getOrderId());
        shipment.setPaymentId(shippingWfReq.getPaymentId());

        shipment = shipmentRepository.save(shipment);

        ShippingWfResp shippingWfResp = new ShippingWfResp(shipment.getShipmentId());
        shippingWfResp.setBusinessProcessId(shippingWfReq.getBusinessProcessId());

        LOGGER.info("COMPLETED SHIPMENT ACTIVITY Business Process ID {}", shippingWfReq.getBusinessProcessId());
        return shippingWfResp;
    }
}
