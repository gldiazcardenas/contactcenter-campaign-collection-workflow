package contactcenterapp;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface PhoneCallContactActivity {

    @ActivityMethod
    String sendPhoneCall(ContactInfo contactInfo);

}
