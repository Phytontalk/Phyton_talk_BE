package like_lion.phytontalk.email;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import like_lion.phytontalk.email.dto.MailAuthenticateRequest;
import like_lion.phytontalk.email.dto.MailAuthenticateResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class MailController {

    private final MailServiceImpl mailServiceImpl;

    @PostMapping("/email")
    public ResponseEntity<MailAuthenticateResponse> sendMail(@RequestBody MailAuthenticateRequest request) {
        MailAuthenticateResponse mailAuthenticateResponse = mailServiceImpl.mailSend(request.email());
        return ResponseEntity.status(HttpStatus.OK).body(mailAuthenticateResponse);
    }
}
