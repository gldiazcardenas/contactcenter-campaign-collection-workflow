package contactcenterapp;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class CollectionCampaignWorkflowImpl implements CollectionCampaignWorkflow {

    private final RetryOptions retryoptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(1))
            .setMaximumInterval(Duration.ofSeconds(100))
            .setBackoffCoefficient(2)
            .setMaximumAttempts(500)
            .build();
    private final ActivityOptions defaultActivityOptions = ActivityOptions.newBuilder()
            // Timeout options specify when to automatically timeout Activities if the process is taking too long.
            .setStartToCloseTimeout(Duration.ofSeconds(5))
            // Optionally provide customized RetryOptions.
            // Temporal retries failures by default, this is simply an example.
            .setRetryOptions(retryoptions)
            .build();

    private final SMSContactActivity smsContact = Workflow.newActivityStub(SMSContactActivity.class, defaultActivityOptions);

    private final EmailContactActivity emailContact = Workflow.newActivityStub(EmailContactActivity.class, defaultActivityOptions);

    private final PhoneCallContactActivity phoneContactContact = Workflow.newActivityStub(PhoneCallContactActivity.class, defaultActivityOptions);

    @Override
    public String contact(String campaignId, ContactInfo contactInfo) {
        String result;

        // 1. Contact via SMS
        result = smsContact.sendSMS(contactInfo);
        Workflow.sleep(Duration.ofSeconds(30));

        if (result == null || !result.equals(REACHED)) {
            // 2. Contact via Email if not successfully reached yet
            result = emailContact.sendEmail(contactInfo);
            Workflow.sleep(Duration.ofSeconds(30));
        }

        if (result == null || !result.equals(REACHED)) {
            // 3. Contact via Phone Call if not successfully reached yet
            result = phoneContactContact.sendPhoneCall(contactInfo);
        }

        return result;
    }

}
