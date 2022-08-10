package contactcenterapp;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface SMSContactActivity {

    @ActivityMethod
    String sendSMS(ContactInfo contactInfo);

}
