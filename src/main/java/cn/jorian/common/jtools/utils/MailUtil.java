package cn.jorian.common.jtools.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * 
 * description mail tools
 *
 * @author juyan.ye
 * @date Jul 3, 2020 : 9:14:12 AM
 *
 */
@Service
public class ToolMail {

	@Autowired
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}")
	private String serverMail;

	/**
	 * send mail to many person who in the mail list<String>
	 * 
	 * @param toUser
	 * @param subject mail subject
	 * @param text    mail content
	 */
	public void sendMail(List<String> toUser, String subject, String text) {
		MimeMessage message = javaMailSender.createMimeMessage();

		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(serverMail);
			helper.setTo(toUser.toArray(new String[toUser.size()]));
			helper.setSubject(subject);
			helper.setText(text, true);

			javaMailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

	}

	/**
	 * send mail to someone
	 * 
	 * @param toUser  to user address
	 * @param subject mail subject
	 * @param text    mail content
	 * @throws MessagingException
	 */
	public void sendMail(String toUser, String subject, String text) throws MessagingException {
		MimeMessage message = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(serverMail);
		helper.setTo(toUser);
		helper.setSubject(subject);
		helper.setText(text, true);
		javaMailSender.send(message);
	}
}
