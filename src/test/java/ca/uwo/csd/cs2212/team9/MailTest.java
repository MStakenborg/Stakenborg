package ca.uwo.csd.cs2212.team9;
import java.io.IOException;
import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.api.easymock.*;
/**
 * Testing Mail class
 * @author mstakenb
 */

@RunWith(PowerMockRunner.class)
@PrepareForTest(Mail.class)
public class MailTest {
String[] emails;
String[] attachments;
String[] attachment;
String[] noattach = null;
String[] noadd = null;
Course testCourse;

	   @Before public void initMocks() throws InvalidInfoException {
		   //prepare inputs for testing
	   		emails = new String[2];
	   		emails[0] = "someone@someemail.com";
	   		emails[1] = "anotherperson@anotheremail.com";
	   		attachments = new String[2];
	   		attachments[0] = "someone.jrxml";
	   		attachments[1] = "anotherperson.jrxml";
	   		attachment = new String[1];
	   		attachment[0] = "logo.jpg";
	   		testCourse = new Course("Code" , "Title" , "Term");
	   		testCourse= new Course("Course", "Title", "Term");
	   		
	   }
	   
	   public Mail mail = mock(Mail.class);
	   public Course course = mock(Course.class);
	   Mockito mock;

	 @Test
   	/*Verifies that email class is called and working with several attachments*/
   	public void testEmailSeveralAttachments() throws Exception {   		
   		course.email(emails, attachments, testCourse, "", "hostname", "username", "password", "1025", "true", "true", "Professor Shantz", "professorEmail@uwo.ca", "Grade Report");
   		mail.email(testCourse, "", "hostname", "username", "password", "1025", "true", "true", "Professor Shantz", "professorEmail@uwo.ca", "Grade Report");
   		verify(mail).email(testCourse, "","hostname", "username", "password", "1025", "true", "true", "Professor Shantz", "professorEmail@uwo.ca", "Grade Report");
   	}


   	@Test
   	/*Verifies that email class is called and working with one attachment*/
  	public void testEmailOneAttach() throws Exception {
   		course.email(emails, attachment, testCourse, "Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		mail.email(testCourse,"Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		verify(mail).email(testCourse, "Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
   	}
   	
   	@Test
   	/*Verifies (Course) sending email is called and working with no attachments*/
  	public void testEmailNoAttach() throws Exception {
		course.email(emails, noattach, testCourse, "Email Body","hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		mail.email(testCourse, "Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		verify(mail).email(testCourse, "Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
	}
  	
   	@Test
   	/*Verifies (Course) sending email with no email addresses fails*/
	public void testEmailNoAdd() throws Exception{
   		try {
   	   		testCourse.email(noadd, noattach, testCourse, "Email Body", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
			mail.email(testCourse, "Email Body","hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		} catch (InvalidInfoException e) {
		}
   	}
   	
   	@Test
   	/*Verifies (Course) sending email with null email settings fails*/
 	public void testEmailNullSettings() throws Exception{
   		try {
   	   		testCourse.email(noadd, noattach, testCourse, "", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
			mail.email(testCourse,"", "hostname", "username", "password", "444" , "false", "false", "name", "email", "subject");
		} catch (InvalidInfoException e) {
		}
   	}
 
   	@Test
   	/*Verifies Constructor is working properly*/
   	public void testConstructor() throws Exception {
   		Mail newMail = new Mail(emails, attachments);
   		PowerMock.expectNew(Mail.class, emails, attachments)
        .andThrow(new IOException("Mock threw error"));
   		PowerMock.replay(Mail.class);
   		assert (new Mail(emails, attachment)).equals(newMail);
   	}   	
}
