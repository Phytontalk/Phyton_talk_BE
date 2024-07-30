package like_lion.phytontalk.dailyquiz;

import java.sql.Timestamp;

public interface DailyQuizService {
    DailyQuiz createDailyQuiz(Timestamp date);
}
