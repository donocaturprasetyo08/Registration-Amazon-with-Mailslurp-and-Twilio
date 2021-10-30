package qaautomation.Test.Utils;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageDeleter;

public class OtpSMSTwilio {
	private static final String ACCOUNT_SID = "account sid";
	private static final String AUTH_TOKEN = "auth token";
	private static final String PHONE_NUMBER = "phone number";
	
	public String getOTP() {
		 Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
		 String smsBody = getMessage();
		 String otp = smsBody.replaceAll("[^-?0-9]+", " ");
		 return otp;
	}
	
	
	public String getMessage() {
		return getMessages().filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
				.filter(m -> m.getTo().equals(PHONE_NUMBER)).map(Message::getBody).findFirst()
				.orElseThrow(() -> new IllegalStateException("Data not found"));
	}
	
	public void deleteMessages(MessageDeleter messagedeleter1){
		 			getMessages()
	                .filter(m -> m.getDirection().compareTo(Message.Direction.INBOUND) == 0)
	                .filter(m -> m.getTo().equals(PHONE_NUMBER))
	                .map(Message::getSid)
	                .map(sid -> Message.deleter(ACCOUNT_SID, sid))
	                .forEach(MessageDeleter::delete);

	    }

	private static Stream<Message> getMessages() {
		ResourceSet<Message> messages = Message.reader(ACCOUNT_SID).read();
		return StreamSupport.stream(messages.spliterator(), false);
	}
}
