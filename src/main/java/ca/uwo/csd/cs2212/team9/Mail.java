package ca.uwo.csd.cs2212.team9;

import java.io.*;
import javax.activation.*;
import java.net.URL;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import org.apache.velocity.*;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

/**
 * @author mstakenb
 * 
 *         Mail class contains methods needed for reading in mail server
 *         properties, sender and receiver information and customized messages
 *         for Gradebook program. Allows for user to select multiple recipients 
 *         and send an individual email to each with corresponding grade report 
 *         or user can select multiple students without an attachment and just
 *         send a generic message to each student selected (or a single student)
 * 
 */

/*Constructor Mail constructs an instance of JavaMail 
 * takes in String array parameters for email addresses of 
 * intended recipients and a list of corresponding attachments
 */
public class Mail {
	private static String[] emails;
	private static String[] attachments;
	private static String SOCKET_FACTORY = "javax.net.ssl.SSLSocketFactory";
	private static String HOST;
	private static String USER;
	private static String PASSWORD;

	public Mail(String[] emails, String[] attachments) {
		this.emails = emails;
		this.attachments = attachments;
	}

	/*
	 * getSession message used in email student method to create a new mail
	 * session and sets username/password authentication
	 */
	private Session getSession(final Properties props) {
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						String username = props.getProperty("smtp.username");
						String password = props.getProperty("smtp.password");
						return new PasswordAuthentication(username, password);
					}
				});
		return session;
	}

	/*
	 * Email method determines whether email will be sent to multiple recipients
	 * in one message or one message per recipient (with attachments for each)
	 */
	public void email(Course active, String emailBody, String hostname,
			String username, String password, String port, String auth,
			String tls, String senderName, String senderEmail, String subject)
			throws Exception {
		Properties props = setProperties(hostname, username, password, port,
				auth, tls, senderName, senderEmail, subject);
		Session session = getSession(props);
		String attachment;
		// no attachments - send multiple students one email
		if (attachments == null) {
			attachment = null;
			sendMultiMessage(session, props, active, attachment, emailBody);
		}
		// one attachment - send multiple students one attachment
		else if (attachments.length == 1 && emails.length > 1) {
			attachment = attachments[0];
			sendMultiMessage(session, props, active, attachment, emailBody);
		}
		// for each email address given, send an email with matching attachment
		// by students username
		else {
			for (int i = 0; i < emails.length; i++) {
				sendMessage(session, props, active, emails[i], emailBody);
			}
		}
	}

	/*
	 * SetProperties method sets the properties file with given smtp server
	 * settings and email sender/recipient information
	 */
	private Properties setProperties(String hostname, String username,
			String password, String port, String auth, String tls,
			String senderName, String senderEmail, String subject) {
		Properties props = new Properties();
		HOST = hostname;
		USER = username;
		PASSWORD = password;
		props.put("mail.smtp.host", hostname);
		props.put("smtp.username", username);
		props.put("smtp.password", password);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", auth);
		props.put("mail.smtp.starttls.enable", tls);
		props.put("sender.name", senderName);
		props.put("sender.email", senderEmail);
		props.put("message.subject", subject);

		props.put("mail.smtp.socketFactory.port", port);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY);
		props.put("mail.smtp.socketFactory.fallback", "false");

		return props;
	}
	
	/*Method getName retrieves the name attribute from the properties file*/
	private String getName(Properties props) {
		String sendName = props.getProperty("sender.name");
		return sendName;
	}
	/*Method getEmail retrieves the email attribute from the properties file*/
	private String getEmail(Properties props) {
		String sendEmail = props.getProperty("sender.email");
		return sendEmail;
	}
	/*Method getSubject retrieves the subject attribute from the properties file*/
	private String getSubject(Properties props) {
		String subject = props.getProperty("message.subject");
		return subject;
	}

	/*
	 * sendMessage method used in email method to instantiate a new mail session
	 * Prepares a mail message for each recipient with individual reports 
	 * attached. Uses getAttachment method to retrieve the appropriate attachment
	 */
	private void sendMessage(Session session, Properties props, Course course,
			String address, String emailBody) throws Exception {
		Message message = new MimeMessage(session);
		String sendName = getName(props);
		String sendEmail = getEmail(props);
		Address sender = new InternetAddress(sendEmail, sendName);
		message.setFrom(sender);

		String attachment = null;
		if (attachments != null) {
			// more than one attachment - match up attachments with correct
			// student//
			if (attachments.length > 1) {
				attachment = getAttachment(address, attachments);
			} else {
				// only one attachment - sending one file to all students in one
				// email//
				attachment = attachments[0];
			}
		}

		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				address));
		String subject = getSubject(props);
		message.setSubject(subject);
		Multipart multi = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();
		text.setText(
				loadTemplate("email.text.vm", course, sendName, emailBody),
				"utf-8");
		MimeBodyPart html = new MimeBodyPart();
		html.setContent(
				loadTemplate("email.html.vm", course, sendName, emailBody),
				"text/html; charset=utf-8");

		// if there's an attachment to send then attach it here
		if (attachment != null) {
			MimeBodyPart attach = new MimeBodyPart();
			attach = bodyAttach(attachment);
			multi.addBodyPart(attach);
		}
		// Add everything to message and send
		multi.addBodyPart(text);
		multi.addBodyPart(html);
		message.setContent(multi);
		Transport transport = session.getTransport("smtp");
		send(message, transport);
	}

	/*
	 * sendMulti Message method instantiate a new mail session
	 * Prepares a single message to multiple recipients with or without a single attachment. Meant for 
	 * a general message to a single or group of students to avoid several messages
	 * in outbox. 
	 */
	private void sendMultiMessage(Session session, Properties props,
			Course course, String attach, String emailBody) throws Exception {
		Message message = new MimeMessage(session);
		String sendName = getName(props);
		String sendEmail = getEmail(props);
		Address sender = new InternetAddress(sendEmail, sendName);
		message.setFrom(sender);

		for (String address : emails)
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					address));
		String subject = getSubject(props);
		message.setSubject(subject);

		Multipart multi = new MimeMultipart();
		MimeBodyPart text = new MimeBodyPart();
		text.setText(
				loadTemplate("email2.text.vm", course, sendName, emailBody),
				"utf-8");
		MimeBodyPart html = new MimeBodyPart();
		html.setContent(
				loadTemplate("email2.html.vm", course, sendName, emailBody),
				"text/html; charset=utf-8");

		// if there's an attachment to send then attach it here
		if (attach != null) {
			MimeBodyPart attachment = new MimeBodyPart();
			attachment = bodyAttach(attach);
			multi.addBodyPart(attachment);
		}
		// Add everything to message and send
		multi.addBodyPart(text);
		multi.addBodyPart(html);
		message.setContent(multi);
		Transport transport = session.getTransport("smtp");
		send(message, transport);
	}

	/* send method sends out the final message */
	private void send(Message message, Transport transport)
			throws InvalidInfoException {
		try {
			transport.connect(HOST, USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException e) {
			throw new InvalidInfoException(
					"There may be something wrong with your email settings. Please check and try again");
		}
	}

	/*
	 * bodyAttach method takes an attachment, reads the data source and returns
	 * the attached file with source and filename information
	 */
	private static MimeBodyPart bodyAttach(String attachment)
			throws MessagingException {
		MimeBodyPart attach = new MimeBodyPart();
		URL attachmentUrl = Mail.class.getClassLoader().getResource(attachment);
		DataSource source = new URLDataSource(attachmentUrl);
		attach.setFileName(attachment);
		attach.setDataHandler(new DataHandler(source));
		return attach;
	}

	/*
	 * loadTemplate fills in email template with given variables to be added at
	 * runtime
	 */
	private static String loadTemplate(String file, Course course, String prof,
			String message) throws InvalidInfoException {
		String code, title, term;
		  code = course.getCode();
		  title = course.getTitle();
		  term = course.getTerm();
		VelocityEngine vel = new VelocityEngine();
		vel.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		vel.setProperty("classpath.resource.loader.class",
				ClasspathResourceLoader.class.getName());
		Template template = vel.getTemplate(file);
		VelocityContext context = new VelocityContext();
		context.put("professor", prof);
		context.put("code", code);
		context.put("title", title);
		context.put("term", term);
		context.put("message", message);
		StringWriter out = new StringWriter();
		template.merge(context, out);
		return out.toString();
	}

	/*
	 * Method getAttachment looks for the attachment that corresponds to the
	 * student's name in the list of attachments - ensures the correct student
	 * gets the correct grade report IF the user correctly names the attachments
	 * to each student's username
	 */
	private static String getAttachment(String recipient, String[] attachments) {
		String attachment = null;
		for (int i = 0; i < attachments.length; i++) {
			String lookforme = attachments[i].substring(0,
					attachments[i].lastIndexOf("."));
			if (recipient.contains(lookforme)) {
				attachment = attachments[i];
				return attachment;
			}
		}
		return attachment;
	}
}
