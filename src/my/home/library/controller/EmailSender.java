package my.home.library.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import my.home.library.model.User;

public class EmailSender {
	private String username;
	private String password;
	private Properties props;
	private static String smtp = "smtp.gmail.com";

	public EmailSender(String username, String password) {
		this.username = username;
		this.password = password;

		props = new Properties();
		props.put("mail.smtp.host", smtp);
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		//props.put("mail.debug", "true");

	}

	public void send(String header, String text, String fromEmail, String toEmail) {
		Session session = Session.getInstance(props, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			// список адресов отправляется однойстрокой через запятую
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(header);
			message.setText(text);

			// session.getTransport().connect(smtp, username, password);

			Transport.send(message);
			System.out.println("Отправлены оповещения на почту");
		} catch (MessagingException e) {
			System.out.println("Ошибка отправки email");
			e.printStackTrace();
		}
	}

	public void sendToAdmins(String header, String text, UsersBase toUsers) {
		StringBuilder admins = new StringBuilder();
		for (User u : toUsers.getUsersList()) {
			if (u.isAdmin()) {
				admins.append(u.getEmail());
				admins.append(", ");
			}
		}
		send(header, text, username, admins.toString());
	}

	public void sendToAll(String header, String text, UsersBase toUsers) {
		StringBuilder users = new StringBuilder();
		for (User u : toUsers.getUsersList()) {
			users.append(u.getEmail());
			users.append(", ");
		}
		send(header, text, username, users.toString());
	}

	public void sendToUser(String header, String text, User user) {
		send(header, text, username, user.getEmail());
	}

}
