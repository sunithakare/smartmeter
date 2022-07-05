package com.envision.common.service;

import javax.mail.internet.InternetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MailService {
	
	@Value("${MailEnabled}")
	boolean mailEnabled;
	
    @Autowired
    private JavaMailSender mailSender;

//    public void sendMail(String from, String subject, String toAddresses, String ccAddresses, String bccAddresses, String body) {
        public void sendMail(String from, String subject, String toAddresses, String body) {
        	if (!mailEnabled) {
        		log.debug("Mail Sending Disabled");
				return;
			}
        MimeMessagePreparator preparator = mimeMessage -> {
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
            message.setTo(toAddresses.split("[,;]"));
            message.setFrom(new InternetAddress(from, false));
            message.setSubject(subject);
//            if (StringUtils.isNotBlank(ccAddresses))
//                message.setCc(ccAddresses.split("[;,]"));
//            if (StringUtils.isNotBlank(bccAddresses))
//                message.setBcc(bccAddresses.split("[;,]"));
            message.setText(body, true);
        };
        mailSender.send(preparator);
    }
}