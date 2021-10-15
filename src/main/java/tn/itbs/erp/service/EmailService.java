package tn.itbs.erp.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;



import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@Service
public class EmailService implements IEmailService{
	
	@Autowired
	private JavaMailSender sender;
	
	@Autowired
	private Configuration config;
	
	public void sendEmail(Map<String, Object> model) {
		
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
			// add attachment
			helper.addAttachment("logo.png", new ClassPathResource("403.png"));
			helper.addInline("logo.png", new ClassPathResource("403.png"));

			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			helper.setTo("gaddour04@gmail.com");
			helper.setText(html, true);
			helper.setSubject("E R P");
			helper.setFrom("siteweb04django@gmail.com");
			sender.send(message);

			

		} catch (MessagingException | IOException | TemplateException e) {
			
		}

		
	}
	

}
