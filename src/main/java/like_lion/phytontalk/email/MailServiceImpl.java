package like_lion.phytontalk.email;

import like_lion.phytontalk.email.dto.MailAuthenticateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService{

    private final JavaMailSender mailSender;

    @Override
    public MailAuthenticateResponse mailSend(String email) {
        String randomCode = generateRandomCode();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Email Verification Code");
        message.setText("Your verification code is: " + randomCode);
        mailSender.send(message);

        return new MailAuthenticateResponse(randomCode);
    }
    @Override
    public String generateRandomCode() {
        Random random = new Random();
        int code = 100000 + random.nextInt(900000);
        return String.valueOf(code);
    }
}
