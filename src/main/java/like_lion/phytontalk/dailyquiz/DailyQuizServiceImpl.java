package like_lion.phytontalk.dailyquiz;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DailyQuizServiceImpl implements DailyQuizService {
    private final DailyQuizRepository dailyQuizRepo;

//    // 특정 날짜에 대한 DailyQuiz 조회
//    public DailyQuiz getDailyQuizByDate(String date) {
//        return dailyQuizRepo.findByDate(date);
//    }

    @Override
    public DailyQuiz createDailyQuiz(Timestamp date) { // 새로운 DailyQuiz 생성
        DailyQuiz dailyQuiz = new DailyQuiz();
        dailyQuiz.setCreatedAt(date);
        return dailyQuizRepo.save(dailyQuiz);
    }

}
