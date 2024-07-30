package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.dailyquiz.DailyQuizRepository;
import like_lion.phytontalk.quiz.Quiz;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectQuizServiceImpl implements SelectQuizService {
    private final DailyQuizRepository dailyQuizRepo;
    private final SelectQuizRepository selectQuizRepo;

    @Override
    public List<QuizResponse> getDailyQuiz() {
        Timestamp today = getTodayDate();
        DailyQuiz dailyQuiz = dailyQuizRepo.findByCreatedAt(today);

        // DailyQuiz가 없는 경우 새로 생성
        if (dailyQuiz == null) {
            dailyQuiz = createDailyQuiz(today);
        }

        // Pageable을 사용하여 SelectQuiz 조회
        Pageable pageable = PageRequest.of(0, 10);
        List<SelectQuiz> selectQuizzes = selectQuizRepo.findByDailyQuizId(dailyQuiz.getDailyQuizId(), pageable).getContent();

        // SelectQuiz를 QuizResponse로 변환
        return selectQuizzes.stream()
                .map(this::convertToQuizResponse)
                .collect(Collectors.toList());
    }

    private DailyQuiz createDailyQuiz(Timestamp today) {
        DailyQuiz dailyQuiz = new DailyQuiz();
        dailyQuiz.setCreatedAt(today); // 또는 필요한 다른 속성을 설정합니다.
        return dailyQuizRepo.save(dailyQuiz);
    }

    public Timestamp getTodayDate() {
        // 현재 날짜와 시간을 Timestamp로 반환 (시간은 00:00:00으로 설정)
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Timestamp.valueOf(now);
    }

    private QuizResponse convertToQuizResponse(SelectQuiz selectQuiz) {
        Quiz quiz = selectQuiz.getQuiz();
        return new QuizResponse(
                quiz.getQuizId(),
                quiz.getOption1(),
                quiz.getOption2(),
                quiz.getType(),
                quiz.getFrequency(),
                quiz.getCreatedAt(),
                quiz.getUpdatedAt()
        );
    }

}
