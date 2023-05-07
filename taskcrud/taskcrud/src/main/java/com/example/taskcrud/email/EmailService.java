package com.example.taskcrud.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
//@AllArgsConstructor
public class EmailService implements EmailSender{
//    Todo: used for logging messages in the EmailService class.
    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
//todo: JavaMailSender is an interface in the Spring Framework that provides a simplified way to send email messages using the JavaMail API.
    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    @Async
    public void send(String to,String email){
        try
        {
//            todo :MimeMessage is a subclass of Message . MIME (Multipurpose Internet Mail Extensions)
//             messages which are used to send messages that contain multimedia content, such as images, audio, and video.
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,"utf-8");
            helper.setText(email,true);
            helper.setTo(to);
            helper.setSubject("Confirm your Email");
            helper.setFrom("Hello@service.com");
            mailSender.send(message);
        }catch (MessagingException e){
//            todo: this catch block is used to handle exceptions that occur when sending email messages using the JavaMail API, log an error message with the details of the exception,
//             and throw a new exception to signal to the caller that the email sending operation failed.
            LOGGER.error("fail to send email",e);
            throw new IllegalStateException("Failed to send email");
        }
    }
}
