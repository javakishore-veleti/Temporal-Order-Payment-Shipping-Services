package com.jk.poc.springboot.temporal.payment.workflow;

import com.jk.poc.springboot.temporal.app_common.dto.PaymentWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.PaymentWfResp;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.PaymentActivity;
import com.jk.poc.springboot.temporal.payment.model.Payment;
import com.jk.poc.springboot.temporal.payment.repositories.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class PaymentActivityImpl implements PaymentActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(PaymentActivityImpl.class);

    private PaymentRepository paymentRepository;

    @Autowired
    public void setPaymentRepository(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public PaymentWfResp processPayment(PaymentWfReq paymentWfReq) {
        LOGGER.info("STARTED PAYMENT ACTIVITY Business Process ID {}", paymentWfReq.getBusinessProcessId());
        PaymentWfResp paymentWfResp = new PaymentWfResp();

        var payment = new Payment();
        payment.setPaymentId(UUID.randomUUID().toString());
        payment.setOrderId(paymentWfReq.getOrderId());

        payment = paymentRepository.save(payment);
        paymentWfResp.setPaymentId(payment.getPaymentId());

        paymentWfResp.setBusinessProcessId(paymentWfReq.getBusinessProcessId());

        LOGGER.info("COMPLETED PAYMENT ACTIVITY Business Process ID {}", paymentWfReq.getBusinessProcessId());
        return paymentWfResp;
    }
}
