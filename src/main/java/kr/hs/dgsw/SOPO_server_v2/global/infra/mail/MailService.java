package kr.hs.dgsw.SOPO_server_v2.global.infra.mail;

import jakarta.mail.internet.MimeMessage;
import kr.hs.dgsw.SOPO_server_v2.global.error.custom.email.UnableToSendEmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;

    public String verificationCode(String toEmail, String title, String authCode){
        try {
            ClassPathResource resource = new ClassPathResource("authEmail.html");
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8));
            String templateContent = reader.lines().collect(Collectors.joining("\n"));
            String emailContent = templateContent.replace("{authCode}", authCode);
            generateEmailForm(toEmail, title, emailContent);
            return authCode;
        }catch (IOException e){
            throw UnableToSendEmailException.EXCEPTION;
        }
    }

    private MimeMessage generateEmailForm(String toEmail,
                                          String title,
                                          String message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(toEmail);
            helper.setSubject(title);
            helper.setText(message, true);
            javaMailSender.send(mimeMessage);
        } catch (Exception e) {
            throw new kr.hs.dgsw.SOPO_server_v2.global.error.custom.auth.UnableToSendEmailException();
        }
        return mimeMessage;
    }
}
