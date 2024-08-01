package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class QuizController {
    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<QuizResponse> createQuiz(@Validated @RequestBody QuizRequest quizRequest) { // 퀴즈 등록
        QuizResponse createQuiz = quizService.createQuiz(quizRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createQuiz);
    }

    @PatchMapping("/{quizId}")
    public ResponseEntity<QuizResponse> updateQuiz(@PathVariable Long quizId, QuizRequest quizRequest) { // 퀴즈 수정
        QuizResponse updateQuiz = quizService.updateQuiz(quizId, quizRequest);
        return ResponseEntity.ok(updateQuiz);
    }

    @DeleteMapping("/{quizId}")
    public ResponseEntity<String> deleteQuiz(@PathVariable Long quizId) { // 퀴즈 삭제
        quizService.deleteQuiz(quizId);
        return ResponseEntity.status(HttpStatus.OK).body("퀴즈 삭제 완료");  // 응답 본문 필요없는 경우 빈 ResponseEntity 객체 생성
    }

    @GetMapping
    public ResponseEntity<List<QuizResponse>> getAllQuiz() { // 퀴즈 전체 조회
        List<QuizResponse> quiz = quizService.getAllQuiz();
        return ResponseEntity.ok(quiz);
    }
}