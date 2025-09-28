package com.jk.poc.springboot.temporal.app_common.workflow.activities;

import com.jk.poc.springboot.temporal.app_common.dto.ShippingWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.ShippingWfResp;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ShippingActivity {

    @ActivityMethod
    ShippingWfResp processShipment(ShippingWfReq shippingWfReq);
}
