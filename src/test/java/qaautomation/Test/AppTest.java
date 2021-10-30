package qaautomation.Test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;
import com.mailslurp.models.Inbox;

import qaautomation.Test.Navigation.CommonPage;
import qaautomation.Test.Page.Login;
import qaautomation.Test.Page.SignUp;
import qaautomation.Test.Utils.FakerData;
import qaautomation.Test.Utils.OtpSMSTwilio;
import qaautomation.Test.Utils.Utils;

/**
 * Unit test for simple App.
 */
public class AppTest extends BaseWeb
{
	private static Email emailCheck;
	private static Inbox inbox;
	private static final Boolean UNREAD_ONLY = true;
	private static String confirmationCode;

	SignUp signUps = new SignUp(driver, explicitWait);
	CommonPage commonPage = new CommonPage(driver, explicitWait);
	Login login = new Login(driver, explicitWait);
	OtpSMSTwilio sms = new OtpSMSTwilio();
	
 @Test
    public void createAccount() throws ApiException
    {
	 // create a real, randomized email address with MailSlurp to represent a user
	 InboxControllerApi inboxControllerApi = new InboxControllerApi(mailslurpClient);
	 inbox = inboxControllerApi.createInbox(null,null,null,null, null, null, null, null, null, null);
	    
	 // check the inbox was created
	 Assert.assertNotNull(inbox.getId());
	 Assert.assertTrue(inbox.getEmailAddress().contains("@mailslurp.com"));
	    
	 // process SignUp data
	 String nama = FakerData.fullname;
	 String password = "helloWord08";
	 String passwordCheck = "helloWord08";
	 String email = inbox.getEmailAddress();
	 signUps.signUpData(nama, email, password, passwordCheck);
	 
	 // receive a verification email from playground using mailslurp
     WaitForControllerApi waitForControllerApi = new WaitForControllerApi(mailslurpClient);
     emailCheck = waitForControllerApi.waitForLatestEmail(null, null, inbox.getId(), null, null, TIMEOUT_MILLIS, UNREAD_ONLY);

     // verify the contents
     Assert.assertTrue(emailCheck.getSubject().contains("Verify your new Amazon account"));
     
     Document doc = Jsoup.parse(emailCheck.getBody());
     confirmationCode = doc.select("p.otp").text();
     
     // verify code
     Assert.assertTrue(confirmationCode.length() == 6);
     signUps.codeVerify(confirmationCode);
     Utils.hardWait(3);
 	 String number = "phonenumber";
     signUps.setNumber(number);
     Utils.hardWait(3);
     String otp = sms.getOTP();
     signUps.setOtp(otp);
     Utils.hardWait(5);
    }
}
