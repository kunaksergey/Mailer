package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ua.shield.entity.ExtScheduleEvent;
import ua.shield.entity.MailServer;
import ua.shield.entity.Task;
import ua.shield.jsf.validator.EmailValidator;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.Future;

/**
 * Created by sa on 12.09.17.
 */
@Service("mailService")
public class MailService {

    @Autowired
    private EventService eventService;

    @Autowired
    private AsyncTaskExecutor taskExecutor;


    //Send Event
    public Future<String> sendEvent(ExtScheduleEvent event) {
        Task task = event.getTask();
        String text = task.getMessage().getText(); //take our text
        String[] emailAddress = task.getGroupMailAddress().getMailAddressSet().stream()
                .filter(e ->
                        e.getEmailAddress().matches(EmailValidator.EMAIL_PATTERN)
                )
                .map(e ->
                        e.getEmailAddress()
                )
                .toArray(String[]::new);
        Set<MailServer> mailServerSet = task.getGroupMailServer().getMailServerSet();

        return sendMail(text, emailAddress, new ArrayList<>(mailServerSet));
    }

    public Future<String> sendMail(final String text, final String[] to, final List<MailServer> mailServerList) {
        return taskExecutor.submit(() -> {
            String log = "";
            int indexServer = 0;
            try {
                sendMailSimple(text, mailServerList.get(indexServer).getSender(), to, mailServerList.get(indexServer));
                log = "Mail was sent successfully over: " + mailServerList.get(indexServer).getHost();
            } catch (MessagingException e) {
                log = "Error create message";
            } catch (MailException e) {
                indexServer++;
                if (mailServerList.size() - 1 <= indexServer) {
                    sendMailSimple(text, mailServerList.get(indexServer).getSender(), to, mailServerList.get(indexServer));
                } else {
                    log = "Failed to send emails;";
                }
            }
            return log;
        });

//        taskExecutor.execute( new Callable<String>() {
//            public void run() {
//                try {
//                    sendMailSimple(message.getText(), mailServer.getSender(), email.getEmailAddress(), mailServer);
//                } catch (MailParseException e) {
//                    e.printStackTrace();
//                    log.error("Failed to send email to: " + to + " reason: "+e.getMessage());
//                    throw new Exception(e);
//                }
//            }
//        });
    }

    private void sendMailSimple(String text, String from, String[] to, MailServer mailServer) throws MailException, MessagingException {
        JavaMailSender mailSender = getJavaMailSender(mailServer);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        // helper.setSubject(subject);
        helper.setText(text);
//            if(filePath != null){
//                FileSystemResource file = new FileSystemResource(filePath);
//                helper.addAttachment(file.getFilename(), file);
//            }


        //send message
        mailSender.send(mimeMessage);
    }


    public JavaMailSenderImpl getJavaMailSender(MailServer mailServer) {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(mailServer.getHost());
        mailSender.setPort(mailServer.getPort());

        mailSender.setUsername(mailServer.getUsername());
        mailSender.setPassword(mailServer.getPassword());

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", mailServer.getProtocol());
        props.put("mail.smtp.auth", mailServer.isSmtpAuth());
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.enable", "true");

        return mailSender;
    }
}


