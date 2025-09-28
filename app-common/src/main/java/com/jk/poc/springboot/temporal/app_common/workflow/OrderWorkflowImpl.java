package com.jk.poc.springboot.temporal.app_common.workflow;

import com.jk.poc.springboot.temporal.app_common.config.TemporalWorkflowHelper;
import com.jk.poc.springboot.temporal.app_common.dto.*;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.OrderActivity;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.PaymentActivity;
import com.jk.poc.springboot.temporal.app_common.workflow.activities.ShippingActivity;
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
    private boolean initialized = false;

    @PostConstruct
    public void init() {
        if(initialized) {
            return;
        }

        LOGGER.info("OrderWorkflowImpl init STARTED");

        methodOptions = new HashMap<>();
        //Not sure what to put in the key here, but it seems to work with the value. Use at your own risk.
        methodOptions.put(TemporalWorkflowHelper.WORKFLOW_ORDER_TASK_QUEUE, ActivityOptions.newBuilder().setHeartbeatTimeout(Duration.ofSeconds(5)).build());

        //setMaximumInterval set to 10 seconds for demo, default is 100 seconds
        retryOptions = RetryOptions.newBuilder().setInitialInterval(Duration.ofSeconds(1)).setMaximumInterval(Duration.ofSeconds(100)).setBackoffCoefficient(2).setMaximumAttempts(500).build();
        defaultActivityOptions = ActivityOptions.newBuilder().setStartToCloseTimeout(Duration.ofSeconds(5)).setRetryOptions(retryOptions).build();

        orderActivity = Workflow.newActivityStub(OrderActivity.class, defaultActivityOptions, methodOptions);

        paymentActivity = Workflow.newActivityStub(PaymentActivity.class, defaultActivityOptions, methodOptions);

        shippingActivity = Workflow.newActivityStub(ShippingActivity.class, defaultActivityOptions, methodOptions);

        initialized = true;
        LOGGER.info("OrderWorkflowImpl init COMPLETED");
    }

    @Override
    public OrderWfRespMaster processOrder(OrderWfReq orderWfReq, OrderWfRespMaster orderWfRespMaster) {
        if(!initialized) {
            this.init();
        }

        LOGGER.info("OrderWorkflowImpl orderActivity.placeOrder STARTED Business Process ID {}", orderWfReq.getBusinessProcessId());

        OrderWfResp createOrderResp = orderActivity.placeOrder(orderWfReq);
        orderWfRespMaster.setOrderWfResp(createOrderResp);
        orderWfRespMaster.setOrderId(createOrderResp.getOrderId());

        LOGGER.info("OrderWorkflowImpl orderActivity.placeOrder COMPLETED Business Process ID {}", orderWfReq.getBusinessProcessId());

        LOGGER.info("OrderWorkflowImpl paymentActivity.processPayment STARTED Business Process ID {}", orderWfReq.getBusinessProcessId());

        PaymentWfReq paymentWfReq = new PaymentWfReq();
        paymentWfReq.setBusinessProcessId(orderWfRespMaster.getBusinessProcessId());
        PaymentWfResp paymentWfResp = paymentActivity.processPayment(paymentWfReq);
        orderWfRespMaster.setPaymentWfResp(paymentWfResp);

        LOGGER.info("OrderWorkflowImpl paymentActivity.processPayment COMPLETED Business Process ID {}", orderWfReq.getBusinessProcessId());

        LOGGER.info("OrderWorkflowImpl shippingActivity.processShipment STARTED Business Process ID {}", orderWfReq.getBusinessProcessId());

        ShippingWfReq shippingWfReq = new ShippingWfReq();
        shippingWfReq.setBusinessProcessId(orderWfRespMaster.getBusinessProcessId());
        ShippingWfResp shippingWfResp = shippingActivity.processShipment(shippingWfReq);
        orderWfRespMaster.setShippingWfResp(shippingWfResp);

        LOGGER.info("OrderWorkflowImpl shippingActivity.processShipment COMPLETED Business Process ID {}", orderWfReq.getBusinessProcessId());
        return orderWfRespMaster;
    }
}
