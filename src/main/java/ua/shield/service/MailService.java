package ua.shield.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import ua.shield.entity.*;
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
    @Async
    public Future<Log> sendEvent(ExtScheduleEvent event) {
        //take our text
        Message message = event.getTask().getMessage();

        String[] emailAddresses = event.getTask().getGroupMailAddress().getMailAddressSet().stream()
                .filter(e ->
                        e.getEmailAddress().matches(EmailValidator.EMAIL_PATTERN))
                .map(MailAddress::getEmailAddress)
                .toArray(String[]::new);

        Set<MailServer> mailServerSet = event.getTask().getGroupMailServer().getMailServerSet();

        Log log = sendMail(new ArrayList<>(mailServerSet), emailAddresses, message);

        log.setOwner(event.getOwner()); //set up owner of log
        log.setEvent(event);//set up event of log
        System.out.println("send in " + Thread.currentThread().getName());
        return new AsyncResult<>(log);
    }

    private Log sendMail(final List<MailServer> mailServerList, final String[] to, final Message message) {

        String strLog = "";
        int indexServer = 0;
        boolean successfull = false;
        while (!successfull && (mailServerList.size() - 1 >= indexServer)) {
            try {
                sendMailSimple(mailServerList.get(indexServer), mailServerList.get(indexServer).getSender(), to, message);
                strLog += "Mail was sent successfully over: " + mailServerList.get(indexServer).getHost();
                successfull = true;
            } catch (MessagingException e) {
                strLog += "Error create message";
            } catch (MailException e) {
                strLog = "Failed to send emails over: " + mailServerList.get(indexServer).getHost();
                indexServer++;
            }
        }

        Log log = new Log();
        log.setLog(strLog);

        return log;
    }

    private void sendMailSimple(MailServer mailServer, String from, String[] to, Message message) throws MailException, MessagingException {
        JavaMailSender mailSender = getJavaMailSender(mailServer);

        MimeMessage mimeMessage = mailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setFrom(from);
        helper.setTo(to);
        // helper.setSubject(subject);
        helper.setSubject(message.getTitle());
        helper.setText(message.getText());

//            if(filePath != null){
//                FileSystemResource file = new FileSystemResource(filePath);
//                helper.addAttachment(file.getFilename(), file);
//            }


        //send message
        mailSender.send(mimeMessage);
    }


    private JavaMailSenderImpl getJavaMailSender(MailServer mailServer) {
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


