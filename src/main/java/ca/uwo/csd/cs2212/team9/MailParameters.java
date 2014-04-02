package ca.uwo.csd.cs2212.team9;

import java.io.Serializable;

/**
 * 
 * @author Tyler
 */
public class MailParameters implements Serializable {
	private String emailBody;
	private String hostname;
	private String username;
	private String password;
	private String port;
	private String auth;
	private String tls;
	private String senderName;
	private String senderEmail;
	private String subject;

	public MailParameters() {
		emailBody = "";
		hostname = "smtp.gmail.com";
		username = "team9gradebook@gmail.com";
		password = "mypassword00";
		port = "465";
		auth = "true";
		tls = "true";
		senderName = "Professor Shantz";
		senderEmail = "team9gradebook@gmail.com";
		subject = "Grade Report";

	}

	public String getEmailBody() {
		return emailBody;
	}

	public String getHostname() {
		return hostname;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getPort() {
		return port;
	}

	public String getAuth() {
		return auth;
	}

	public String getTls() {
		return tls;
	}

	public String getSenderName() {
		return senderName;
	}

	public String getSenderEmail() {
		return senderEmail;
	}

	public String getSubject() {
		return subject;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public void setTls(String tls) {
		this.tls = tls;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
