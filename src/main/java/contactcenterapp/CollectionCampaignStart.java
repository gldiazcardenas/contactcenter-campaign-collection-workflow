package contactcenterapp;

import io.temporal.api.common.v1.WorkflowExecution;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.util.UUID;

public class CollectionCampaignStart {

    public static void main(String[] arg) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setTaskQueue(CollectionCampaignWorkflow.QUEUE_TASK_NAME)
                .setWorkflowId("collection-campaign-workflow")
                .build();

        // WorkflowClient can be used to start, signal, query, cancel, and terminate Workflows.
        WorkflowClient client = WorkflowClient.newInstance(service);

        // WorkflowStubs enable calls to methods as if the Workflow object is local, but actually perform an RPC.
        CollectionCampaignWorkflow workflow = client.newWorkflowStub(CollectionCampaignWorkflow.class, options);

        String campaignId = UUID.randomUUID().toString();
        String contactId = UUID.randomUUID().toString();

        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setId(contactId);
        contactInfo.setName("Gabriel Diaz");
        contactInfo.setEmailAddress("gabriel@email.com");
        contactInfo.setPhoneNumber("+57300000000");

        // Asynchronous execution. This process will exit after making this call.
        WorkflowExecution we = WorkflowClient.start(workflow::contact, campaignId, contactInfo);

        System.out.printf("\nContacted %s from campaign %s\n", contactId, campaignId);
        System.out.printf("\nWorkflowID: %s RunID: %s", we.getWorkflowId(), we.getRunId());

        System.exit(0);
    }

}
