package com.springboot.excel.service.impl;

import com.springboot.excel.model.Email;
import com.springboot.excel.model.Student;
import com.springboot.excel.repository.StudentRepository;
import com.springboot.excel.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private ThreadPoolExecutor threadPoolExecutor;
    @Autowired
    private StudentRepository studentRepository;
    @Value("${spring.mail.username}")
    private String fromEmail;
    @Value("${email.dummy.subject}")
    private String emailSubject;
    @Value("${email.dummy.message}")
    private String emailMessage;

    @Override
    public void sendEmailAsync(final Email email) {
        threadPoolExecutor.submit(() -> {
            try {
                sendEmail(email);
         //      each email is sent through different thread
                //       System.out.println(Thread.currentThread().getId());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void populateAndSendEmail() {
        List<Student> studentList = studentRepository.getStudents();
        for (Student student : studentList) {
         String toMail=student.getEmail();
         Email email = new Email();
            email.setFrom(fromEmail);
            email.setTo(toMail);
            email.setSubject(emailSubject);
            email.setMessageText(emailMessage);
            try {
                sendEmailAsync(email);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    private void sendEmail(final Email email) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new
                MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setSubject(email.getSubject());
        mimeMessageHelper.setFrom(email.getFrom());
        mimeMessageHelper.setTo(email.getTo());
        mimeMessageHelper.setText(email.getMessageText());
        javaMailSender.send(mimeMessage);
    }
}


