package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.quiz.dto.QuizResponse;

import java.util.List;

public interface SelectQuizService {
    List<QuizResponse> getDailyQuiz();
}
