package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;

import java.util.List;

public interface QuizService {
    QuizResponse createQuiz(QuizRequest quizRequest);
    QuizResponse updateQuiz(Long id, QuizRequest quizRequest);
    void deleteQuiz(Long id);
    List<QuizResponse> getAllQuiz();
}
