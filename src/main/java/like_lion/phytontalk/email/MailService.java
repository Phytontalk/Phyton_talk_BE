package like_lion.phytontalk.email;

import like_lion.phytontalk.email.dto.MailAuthenticateResponse;
import org.springframework.stereotype.Service;

public interface MailService {
    MailAuthenticateResponse mailSend(String email);
    String generateRandomCode();
}
