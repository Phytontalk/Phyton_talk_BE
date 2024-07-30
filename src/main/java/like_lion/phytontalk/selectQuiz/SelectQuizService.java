package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;

import java.sql.Timestamp;
import java.util.List;

public interface SelectQuizService {
    List<QuizResponse> getDailyQuiz();
    Timestamp getTodayDate();
}
