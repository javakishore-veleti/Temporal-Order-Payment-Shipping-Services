package com.jk.poc.springboot.temporal.app_common.workflow.util;

import io.temporal.client.WorkflowOptions;

public class TemporalWfClientFactory {

    // TemporalWfHelper.ORDER_LIFECYCLE_WORKFLOW_TASK_QUEUE
    public static <T> T createWfClient(Class<T> workflowInterfaceClass, String taskQueue) {

        var workflowClient = TemporalWfHelper.getWorkflowClient();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(taskQueue).build();

        // OrderWorkflow workflow = workflowClient.newWorkflowStub(OrderWorkflow.class, options);
        T workflow = workflowClient.newWorkflowStub(workflowInterfaceClass, options);

        /*
        // Asynchronously start the workflow execution
        WorkflowClient.start(workflow::processOrder, request.customerId(), request.items(), amount);
         */

        return workflow;
    }
}
