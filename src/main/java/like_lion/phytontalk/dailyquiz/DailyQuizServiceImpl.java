package like_lion.phytontalk.dailyquiz;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(readOnly = true)
public class DailyQuizServiceImpl implements DailyQuizService {
    private final DailyQuizRepository dailyQuizRepo;

    @Override
    @Transactional
    public DailyQuiz createDailyQuiz(LocalDateTime today) { // 새로운 DailyQuiz 생성
        DailyQuiz dailyQuiz = new DailyQuiz();
        dailyQuiz.setCreatedAt(today);
        return dailyQuizRepo.save(dailyQuiz);
    }

    @Override
    public DailyQuiz findByCreatedAt(LocalDateTime createdAt) {
        return dailyQuizRepo.findByCreatedAt(createdAt);
    }
}
