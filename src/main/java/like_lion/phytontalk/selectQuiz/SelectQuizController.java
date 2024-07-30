package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("quiz")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectQuizController {
    private final SelectQuizService sqs;
// 지금 quizResponse랑 SelectQuizService가 혼용되어 있음 확인 필요
    @GetMapping("/daily")
    public ResponseEntity<List<QuizResponse>> getDailyQuiz() { // 오늘의 퀴즈 조회
        List<QuizResponse> quiz = sqs.getDailyQuiz();
        return ResponseEntity.ok(quiz);
    }
}