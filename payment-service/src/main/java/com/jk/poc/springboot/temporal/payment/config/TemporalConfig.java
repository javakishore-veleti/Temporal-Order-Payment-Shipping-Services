package com.jk.poc.springboot.temporal.payment.config;

import com.jk.poc.springboot.temporal.app_common.config.TemporalWorkflowHelper;
import com.jk.poc.springboot.temporal.app_common.workflow.OrderWorkflowImpl;
import com.jk.poc.springboot.temporal.payment.workflow.PaymentActivityImpl;
import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TemporalConfig {

    @Value("${temporal.target_host_ip:localhost:7233}")
    private String temporalTargetHostAndIp = "localhost:7233";

    @Autowired
    private PaymentActivityImpl paymentActivity;

    @PostConstruct
    public void startWorkers() {
        var stub = WorkflowServiceStubs.newServiceStubs(WorkflowServiceStubsOptions.newBuilder().setEnableHttps(false).setTarget(temporalTargetHostAndIp).build());
        var client = WorkflowClient.newInstance(stub);

        var factory = WorkerFactory.newInstance(client);
        Worker worker = factory.newWorker(TemporalWorkflowHelper.ORDER_LIFECYCLE_WORKFLOW_TASK_QUEUE);
        worker.registerWorkflowImplementationTypes(OrderWorkflowImpl.class);
        worker.registerActivitiesImplementations(paymentActivity);
        factory.start();
    }
}
