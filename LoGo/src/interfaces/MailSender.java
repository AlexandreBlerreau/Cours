/*****
 * @author Blerreau Alexandre
*/
package interfaces;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {
	
	
	private static String to;
	private static String from;
	private static String subject;
	private static String message;
	//private static  String mailSmtpHost = "smtps.univ-lille1.fr";
	
	private static  String mailSmtpHost = "smtp.gmail.com";
	
	
	/*****
	 * @param subject String
	 * @param message String
	 */
	public MailSender(String subject, String message) {
		this.to = "bug.report.modeK5@gmail.com";
		this.subject = subject;
		this.message = message;
		this.from = "bug.report.modeK5@gmail.com";
	}
	
	
	/*****
	 * @param MailSender send
	 * Envoie un mail
	 */
		public void send() {

		sendEmail(to, null, from, subject, message, mailSmtpHost);
		
		}
		
	
		/*****
		 * @param MailSender sendEmail
		 * Prépare est gére les protocoles pour l'envois de l'email aux developpeurs
		 */
	public  void sendEmail(String to, String cc, String from, String subject, String text, String smtpHost) {
		try {
			Properties properties = new Properties();
			properties.put("mail.smtp.host", smtpHost);
			properties.put("mail.smtp.auth", true);
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
			
			
			Session emailSession = Session.getDefaultInstance(properties,
					new javax.mail.Authenticator(){
		        protected PasswordAuthentication getPasswordAuthentication() {
		            return new PasswordAuthentication(
		            		"bug.report.modeK5@gmail.com", "beaufils42");
		        }
		});

			Message emailMessage = new MimeMessage(emailSession);
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			//emailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
			emailMessage.setFrom(new InternetAddress(from));
			emailMessage.setSubject(subject);
			emailMessage.setText(text);
			
			emailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse("baptiste.lewandoski@etudiant.univ-lille1.fr"));
			emailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse("guillaume1.renaud@etudiant.univ-lille1.fr"));
			emailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse("florian.rohaert@etudiant.univ-lille1.fr"));
			emailMessage.addRecipients(Message.RecipientType.CC, InternetAddress.parse("alexandre.blerreau@etudiant.univ-lille1.fr"));
		

			//emailSession.setDebug(true);
			
			Transport t = emailSession.getTransport("smtp");
			
			t.connect("smtp.gmail.com",465,"bug.report.modeK5@gmail.com", "beaufils42");
			
			t.send(emailMessage);
			
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
}
