package like_lion.phytontalk.answer;

import org.springframework.transaction.annotation.Transactional;

public interface AnswerService {
    //    private final QuizRepository quizRepository;
    //    private final DailyQuizService dailyQuizService;
        @Transactional
        void saveAnswer(Long memberId, AnswerRequset answerRequset);
}
