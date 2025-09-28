package com.jk.poc.springboot.temporal.app_common.workflow.activities;

import com.jk.poc.springboot.temporal.app_common.dto.PaymentWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.PaymentWfResp;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PaymentActivity {

    @ActivityMethod
    PaymentWfResp processPayment(PaymentWfReq paymentWfReq);
}
