package com.jk.poc.springboot.temporal.order.facades;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfRespMaster;
import com.jk.poc.springboot.temporal.app_common.workflow.util.TemporalWfClientFactory;
import com.jk.poc.springboot.temporal.app_common.workflow.util.TemporalWfHelper;
import com.jk.poc.springboot.temporal.order.workflow.OrderWorkflowImpl;
import org.springframework.stereotype.Service;
import io.temporal.client.WorkflowClient;

@Service
public class OrdersWfFacadeImpl implements OrdersWfFacade {

    @Override
    public void createOrder(OrderWfRespMaster orderWfRespMaster, OrderWfReq orderWfReq) {
        OrderWorkflowImpl workflow = TemporalWfClientFactory.createWfClient(OrderWorkflowImpl.class, TemporalWfHelper.ORDER_LIFECYCLE_WORKFLOW_TASK_QUEUE);

        // Asynchronously start the workflow execution
        WorkflowClient.start(workflow::processOrder, orderWfReq, orderWfRespMaster);

    }
}
