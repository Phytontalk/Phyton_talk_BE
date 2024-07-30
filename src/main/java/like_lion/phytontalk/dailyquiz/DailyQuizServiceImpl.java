package like_lion.phytontalk.dailyquiz;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class DailyQuizServiceImpl implements DailyQuizService {
    private final DailyQuizRepository dailyQuizRepo;

    @Override
    @Transactional
    public DailyQuiz createDailyQuiz(Timestamp today) { // 새로운 DailyQuiz 생성
        DailyQuiz dailyQuiz = new DailyQuiz();
        dailyQuiz.setCreatedAt(today);
        return dailyQuizRepo.save(dailyQuiz);
    }

    @Override
    public DailyQuiz findByCreatedAt(Timestamp createdAt) {
        return dailyQuizRepo.findByCreatedAt(createdAt);
    }
}
