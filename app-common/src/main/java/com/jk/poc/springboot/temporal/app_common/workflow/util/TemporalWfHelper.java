package com.jk.poc.springboot.temporal.app_common.workflow.util;

import com.jk.poc.springboot.temporal.app_common.config.TemporalConfig;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;

public class TemporalWfHelper {

    public static final String WORKFLOW_ORDER_TASK_QUEUE = "WorkflowOrderTaskQueue";
    public static final String ORDER_LIFECYCLE_WORKFLOW_TASK_QUEUE = "OrderLifecycleWorkflowTaskQueue";

    public static WorkflowClient getWorkflowClient() {
        WorkflowServiceStubs service = WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder()
                .setEnableHttps(false)
                .setTarget(TemporalConfig.getTemporalServerHostAndIp())
                .build());
        return WorkflowClient.newInstance(service);
    }
}
