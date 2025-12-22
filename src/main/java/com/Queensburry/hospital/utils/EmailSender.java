package com.Queensburry.hospital.utils;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendRegistrationEmail(String toEmail, Map<String, Object> variables) throws MessagingException {


        String html = loadHtmlTemplate("src/main/resources/templates/registration-success.html");


        html = html.replace("{{patientName}}", variables.get("patientName").toString());
        html = html.replace("{{patientId}}", variables.get("patientId").toString());
        html = html.replace("{{clinicName}}", variables.get("clinicName").toString());
        html = html.replace("{{registrationDate}}", variables.get("registrationDate").toString());
        html = html.replace("{{loginUrl}}", variables.get("loginUrl").toString());
        html = html.replace("{{year}}", variables.get("year").toString());

        MimeMessage msg = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(msg, "utf-8");

        helper.setTo(toEmail);
        helper.setSubject("Registration Successful - " + variables.get("clinicName"));
        helper.setText(html, true);
        helper.setFrom("noreply@" + variables.get("clinicDomain"));

        mailSender.send(msg);
    }

    private String loadHtmlTemplate(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (Exception e) {
            throw new RuntimeException("Failed to load email template", e);
        }
    }



    public void sendAppointmentEmail(String toEmail, Map<String, String> variables) throws MessagingException {
        try {

            ClassPathResource resource = new ClassPathResource("templates/email-templates.html");
            String htmlContent = StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);

            // Replace placeholders in the template
            for (Map.Entry<String, String> entry : variables.entrySet()) {
                htmlContent = htmlContent.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            // Prepare email
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
            helper.setText(htmlContent, true); // true = HTML
            helper.setTo(toEmail);
            helper.setSubject("Appointment Confirmation");
            helper.setFrom("noreply@" + variables.get("clinicName").toLowerCase().replaceAll(" ", "") + ".com");

            // Send email
            mailSender.send(message);

        } catch (Exception e) {
            e.printStackTrace();
            throw new MessagingException("Failed to send email: " + e.getMessage());
        }
    }



    public void sendLabAppointmentEmail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

















}
