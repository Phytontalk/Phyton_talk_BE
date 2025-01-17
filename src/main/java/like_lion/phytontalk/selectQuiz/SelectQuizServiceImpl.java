package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.dailyquiz.DailyQuizService;
import like_lion.phytontalk.quiz.Quiz;
import like_lion.phytontalk.quiz.QuizRepository;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SelectQuizServiceImpl implements SelectQuizService {
    private final DailyQuizService dailyQuizService;
    private final SelectQuizRepository selectQuizRepo;
    private final QuizRepository quizRepo;

    @Override
    public DailyQuizResponse getDailyQuiz() {
        LocalDateTime today = LocalDateTime.now();
        DailyQuiz dailyQuiz = dailyQuizService.findByCreatedAt(today);

        // DailyQuiz가 없는 경우 새로 생성
        if (dailyQuiz == null) {
            selectQuizzes(dailyQuiz, today);
            dailyQuiz = dailyQuizService.findByCreatedAt(today);
        }

        // SelectQuiz 조회
        Pageable pageable = PageRequest.of(0, 10);
        List<SelectQuiz> selectQuizzes = selectQuizRepo.findByDailyQuizId(dailyQuiz.getDailyQuizId(), pageable).getContent();

        log.info("selectQuizzes: {}", selectQuizzes);
        List<DailyQuizResponse.Question> quizResponses = selectQuizzes.stream()
                .map(this::convertToDailyQuizResponse)
                .collect(Collectors.toList());
        // frequency 업데이트


        if (!quizResponses.isEmpty()) {
            updateQuizFrequency(selectQuizzes);
        }

        return new DailyQuizResponse(quizResponses);
    }
    public void selectQuizzes(DailyQuiz dailyQuizToCreate, LocalDateTime today) {
        dailyQuizToCreate = dailyQuizService.createDailyQuiz(today);
        log.info("created dailyQuiz: {}", dailyQuizToCreate);

        Pageable pageable = PageRequest.of(0, 10);
        List<Quiz> selectQuizzes = selectQuizRepo.findDailyQuizzes(pageable).getContent();

        // 퀴즈를 SelectQuiz 테이블에 삽입
        for (Quiz quiz : selectQuizzes) {
            SelectQuiz selectQuiz = new SelectQuiz();
            selectQuiz.setQuiz(quiz);
            selectQuiz.setDailyQuiz(dailyQuizToCreate);
            selectQuizRepo.save(selectQuiz);
        }
        log.info("create selected quizzes: {}", selectQuizzes);
    }

    private void updateQuizFrequency(List<SelectQuiz> selectQuizzes) {    // 빈도 수 증가
        for (SelectQuiz selectQuiz : selectQuizzes) {
            Quiz quiz = selectQuiz.getQuiz();
            quiz.setFrequency(quiz.getFrequency() + 1);
            quizRepo.save(quiz);
        }
    }

    private DailyQuizResponse.Question convertToDailyQuizResponse(SelectQuiz selectQuiz){
        Quiz quiz = selectQuiz.getQuiz();
        List<String> options = new ArrayList<>();
        options.add(quiz.getOption1());
        options.add(quiz.getOption2());
        DailyQuizResponse.Question dailyQuizResponse = new DailyQuizResponse.Question(quiz.getQuizId(), options);
        System.out.println(dailyQuizResponse);
        return dailyQuizResponse;
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
