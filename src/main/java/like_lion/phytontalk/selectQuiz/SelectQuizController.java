package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectQuizController {
    private final SelectQuizService selectQuizService;

    @GetMapping("/daily")
    public ResponseEntity<DailyQuizResponse> getDailyQuiz() { // 오늘의 퀴즈 조회
        DailyQuizResponse quiz = selectQuizService.getDailyQuiz();
        return ResponseEntity.status(HttpStatus.OK).body(quiz);
    }
}