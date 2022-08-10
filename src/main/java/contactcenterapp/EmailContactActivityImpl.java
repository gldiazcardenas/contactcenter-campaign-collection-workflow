package contactcenterapp;

public class EmailContactActivityImpl implements EmailContactActivity {
    @Override
    public String sendEmail(ContactInfo contactInfo) {
        return CollectionCampaignWorkflow.NOT_REACHED;
    }
}
