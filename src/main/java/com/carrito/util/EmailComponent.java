package com.carrito.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Random;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import com.carrito.dto.Email;

import lombok.Data;

@Component
public class EmailComponent {

	Logger logger = LoggerFactory.getLogger(EmailComponent.class);

	public void enviar(Email email) {

		this.enviar(email, false);
	}

	public void enviar(Email email, boolean lanzarError) {

		MimeMessagePreparator messagePreparator = mimeMessage -> {
			
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, StandardCharsets.UTF_8.displayName());
			
			//MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setTo(email.getDestinatario());
			messageHelper.setSubject(email.getAsunto());
			
			if(email.getAdjunto()!=null) {
				
				messageHelper.addAttachment("adjunto."+email.getExtencion(), new ByteArrayResource(IOUtils.toByteArray(email.getAdjunto())));
				//messageHelper.addInline("url", new InputStreamResource(email.getAdjunto()));
			}
			if(email.getUrl()!=null){
				messageHelper.setText(this.build(email.getDescripcion()), true);
				
				FileSystemResource file = new FileSystemResource(email.getUrl());
				messageHelper.addInline("url", file);
							
			}else {
				
				messageHelper.setText(this.buildAuxiliar(email.getDescripcion()), true);
			}
			//messageHelper.addAttachment(attachmentFilename, dataSource);
		};

		try {

			JavaMailSender emailSender = this.crearJavaMailSender();
			emailSender.send(messagePreparator);

		} catch (MailException e) {
			logger.error(email.toString());
			e.printStackTrace();

			if (lanzarError)
				throw e;
		}
	}

	private String build(String descripcion) throws Exception {

		String plantillaCorreo = this
				.obtenerTextoDeArchivo(new ClassPathResource("static/correo.html").getFile());

		plantillaCorreo = plantillaCorreo.replaceAll(":descripcion", descripcion);

		return plantillaCorreo;
	}

	private String buildAuxiliar(String descripcion) throws Exception {

		String plantillaCorreo = this
				.obtenerTextoDeArchivo(new ClassPathResource("static/email_template.html").getFile());

		plantillaCorreo = plantillaCorreo.replaceAll(":descripcion", descripcion);

		return plantillaCorreo;
	}

	
	
	private String obtenerTextoDeArchivo(File file) {

		FileReader fr = null;
		BufferedReader br = null;

		String contenido = "";

		try {

			fr = new FileReader(file.getPath());
			br = new BufferedReader(fr);

			String linea = "";

			while ((linea = br.readLine()) != null) {
				contenido += linea;
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {
				br.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contenido;
	}

	private JavaMailSender crearJavaMailSender() {

		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		UserPass email = this.getEmailRandom();
		// Using gmail
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername(email.getUser());
		mailSender.setPassword(email.getPass());

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");

		mailSender.setJavaMailProperties(javaMailProperties);

		return mailSender;
	}

	private UserPass getEmailRandom() {

		UserPass emails[] = { new UserPass("u20181168382@usco.edu.co", "danyertous123") };

		Random random = new Random();
		int n = random.nextInt(emails.length);

		UserPass email = emails[n];

		return email;
	}

	@Data
	private class UserPass {

		private String user;
		private String pass;

		public UserPass(String user, String pass) {
			this.user = user;
			this.pass = pass;
		}
	}
}
