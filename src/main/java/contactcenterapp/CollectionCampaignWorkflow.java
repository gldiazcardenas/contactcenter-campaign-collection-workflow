package contactcenterapp;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CollectionCampaignWorkflow {

    String QUEUE_TASK_NAME = "COLLECTION_CAMPAIGN";

    @WorkflowMethod
    String contact(String campaignId, ContactInfo contactInfo);

}
