package like_lion.phytontalk.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerServiceImpl answerService;

    @PostMapping("/answer/{memberId}")
    public ResponseEntity<String> saveAnswer(@PathVariable(name = "memberId") Long memberId,
                                             @RequestBody AnswerRequset answerRequset){
        answerService.saveAnswer(memberId, answerRequset);
        return ResponseEntity.status(HttpStatus.CREATED).body("문제 저장 완료");
    }
}
