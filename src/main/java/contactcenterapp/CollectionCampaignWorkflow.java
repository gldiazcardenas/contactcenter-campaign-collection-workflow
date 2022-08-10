package contactcenterapp;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

@WorkflowInterface
public interface CollectionCampaignWorkflow {

    String REACHED = "REACHED";
    String NOT_REACHED = "NOT_REACHED";
    String QUEUE_TASK_NAME = "COLLECTION_CAMPAIGN";

    @WorkflowMethod
    String contact(String campaignId, ContactInfo contactInfo);

}
