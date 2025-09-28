package com.jk.poc.springboot.temporal.order.facades;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfRespMaster;
import com.jk.poc.springboot.temporal.app_common.workflow.OrderWorkflow;
import com.jk.poc.springboot.temporal.app_common.workflow.util.TemporalWfClientFactory;
import com.jk.poc.springboot.temporal.app_common.workflow.util.TemporalWfHelper;
import org.springframework.stereotype.Service;
import io.temporal.client.WorkflowClient;

import java.util.UUID;

@Service
public class OrdersWfFacadeImpl implements OrdersWfFacade {

    @Override
    public void createOrder(OrderWfRespMaster orderWfRespMaster, OrderWfReq orderWfReq) {
        String customWfId = UUID.randomUUID().toString();

        OrderWorkflow workflow = TemporalWfClientFactory.createWfClient(OrderWorkflow.class, TemporalWfHelper.ORDER_LIFECYCLE_WORKFLOW_TASK_QUEUE, customWfId);
        orderWfReq.setWorkflowId(customWfId);
        orderWfRespMaster.setWorkflowId(customWfId);

        // Asynchronously start the workflow execution
        WorkflowClient.start(workflow::processOrder, orderWfReq, orderWfRespMaster);
    }
}
