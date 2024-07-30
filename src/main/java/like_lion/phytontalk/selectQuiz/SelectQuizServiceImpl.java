package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.dailyquiz.DailyQuizService;
import like_lion.phytontalk.quiz.Quiz;
import like_lion.phytontalk.quiz.QuizRepository;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectQuizServiceImpl implements SelectQuizService {
    private final DailyQuizService dailyQuizService;
    private final SelectQuizRepository selectQuizRepo;
    private final QuizRepository quizRepo;

    @Override
    public List<QuizResponse> getDailyQuiz() {
        Timestamp today = getTodayDate();
        DailyQuiz dailyQuiz = dailyQuizService.findByCreatedAt(today);

        // DailyQuiz가 없는 경우 새로 생성
        if (dailyQuiz == null) {
            dailyQuiz = dailyQuizService.createDailyQuiz(today);
        }

        // Pageable을 사용하여 SelectQuiz 조회
        Pageable pageable = PageRequest.of(0, 10);
        List<SelectQuiz> selectQuizzes = selectQuizRepo.findByDailyQuizId(dailyQuiz.getDailyQuizId(), pageable).getContent();

        // SelectQuiz를 QuizResponse로 변환
        List<QuizResponse> quizResponses = selectQuizzes.stream()
                .map(this::convertToQuizResponse)
                .collect(Collectors.toList());

        // 빈 리스트가 아닐 경우 frequency를 업데이트
        if (!quizResponses.isEmpty()) {
            updateQuizFrequencies(selectQuizzes);
        }

        return quizResponses;
    }

    private Timestamp getTodayDate() {
        // 현재 날짜와 시간을 Timestamp로 반환 (시간은 00:00:00으로 설정)
        LocalDateTime now = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0).withNano(0);
        return Timestamp.valueOf(now);
    }

    private void updateQuizFrequencies(List<SelectQuiz> selectQuizzes) {    // 빈도 수 증가
        for (SelectQuiz selectQuiz : selectQuizzes) {
            Quiz quiz = selectQuiz.getQuiz();
            quiz.setFrequency(quiz.getFrequency() + 1);
            quizRepo.save(quiz);
        }
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
