package com.jk.poc.springboot.temporal.order.workflow;

import com.jk.poc.springboot.temporal.app_common.dto.*;
import com.jk.poc.springboot.temporal.app_common.workflow.OrderWorkflow;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.OrderActivity;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.PaymentActivity;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.ShippingActivity;
import com.jk.poc.springboot.temporal.order.config.TemporalWorkflowHelper;
import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class OrderWorkflowImpl implements OrderWorkflow {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderWorkflowImpl.class);

    private OrderActivity orderActivity;
    private ShippingActivity shippingActivity;
    private PaymentActivity paymentActivity;

    private RetryOptions retryOptions;
    private ActivityOptions defaultActivityOptions = null;
    private Map<String, ActivityOptions> methodOptions;

    @PostConstruct
    public void init() {
        LOGGER.info("OrderWorkflowImpl init STARTED");

        methodOptions = new HashMap<>();
        //Not sure what to put in the key here, but it seems to work with the value. Use at your own risk.
        methodOptions.put(TemporalWorkflowHelper.WORKFLOW_ORDER_TASK_QUEUE, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());

        //setMaximumInterval set to 10 seconds for demo, default is 100 seconds
        retryOptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1)).setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(500).build();
        defaultActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).setRetryOptions(retryOptions).build();

        orderActivity = Workflow.newActivityStub(OrderActivity.class, defaultActivityOptions, methodOptions);

        LOGGER.info("OrderWorkflowImpl init COMPLETED");
    }

    @Override
    public OrderWfRespMaster processOrder(OrderWfReq orderWfReq) {
        OrderWfRespMaster orderWfRespMaster = new OrderWfRespMaster();

        OrderWfResp createOrderResp = orderActivity.placeOrder(orderWfReq);
        orderWfRespMaster.setOrderWfResp(createOrderResp);

        PaymentWfReq paymentWfReq = new PaymentWfReq();
        PaymentWfResp paymentWfResp = paymentActivity.processPayment(paymentWfReq);
        orderWfRespMaster.setPaymentWfResp(paymentWfResp);

        ShippingWfReq shippingWfReq = new ShippingWfReq();
        ShippingWfResp shippingWfResp = shippingActivity.processShipment(shippingWfReq);
        orderWfRespMaster.setShippingWfResp(shippingWfResp);

        return orderWfRespMaster;
    }
}
