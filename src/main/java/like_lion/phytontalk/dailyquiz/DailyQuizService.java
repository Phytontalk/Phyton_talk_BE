package like_lion.phytontalk.dailyquiz;

import java.time.LocalDateTime;

public interface DailyQuizService {
    DailyQuiz createDailyQuiz(LocalDateTime today);
    DailyQuiz findByCreatedAt(LocalDateTime createdAt);
}
