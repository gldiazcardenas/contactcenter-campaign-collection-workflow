package contactcenterapp;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface EmailContactActivity {

    @ActivityMethod
    String sendEmail(ContactInfo contactInfo);

}
