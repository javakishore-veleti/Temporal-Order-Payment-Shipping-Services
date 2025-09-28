package com.jk.poc.springboot.temporal.app_common.workflow;

import com.jk.poc.springboot.temporal.app_common.dto.OrderWfReq;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfResp;
import com.jk.poc.springboot.temporal.app_common.dto.OrderWfRespMaster;
import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface OrderWorkflow {

    @WorkflowMethod
    void processOrder(OrderWfReq orderWfReq, OrderWfRespMaster orderWfResp);
}
