package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepo;

    @Override
    @Transactional
    public QuizResponse createQuiz(QuizRequest quizRequest) { // 퀴즈 생성
        Quiz quiz = new Quiz();
        LocalDateTime currentTime = LocalDateTime.now();
        quiz.setOption1(quizRequest.getOption1());
        quiz.setOption2(quizRequest.getOption2());
        quiz.setType(quizRequest.getType());
        quiz.setCreatedAt(currentTime);

        Quiz savedQuiz = quizRepo.save(quiz);   // request로 받은 객체 저장
        return mapToResponseDto(savedQuiz);
    }

    @Override
    @Transactional
    public QuizResponse updateQuiz(Long id, QuizRequest quizRequest) { // 퀴즈 수정
        Optional<Quiz> quiz = quizRepo.findById(id);
        quiz.orElseThrow(()-> new RuntimeException("Quiz not found"));
        Quiz findQuiz = quiz.get();
        LocalDateTime currentTime = LocalDateTime.now();

        if (quizRequest.getOption1() != null) {
            findQuiz.setOption1(quizRequest.getOption1());
        }
        if (quizRequest.getOption2() != null) {
            findQuiz.setOption2(quizRequest.getOption2());
        }
        if (quizRequest.getType() != null) {
            findQuiz.setType(quizRequest.getType());
        }
        findQuiz.setUpdatedAt(currentTime);

        log.info("to update Quiz {}", quizRequest.getType());
        log.info("Updating Quiz {}", findQuiz.getType());

        return mapToResponseDto(findQuiz);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) { // 퀴즈 삭제
        Optional<Quiz> quiz = quizRepo.findById(id);
        quiz.orElseThrow(()-> new RuntimeException("Quiz not found"));
        // dailyquiz에 있을 경우 삭제 불가(예외처리 메시지 추가 필요)
        quizRepo.deleteById(id);
    }

    @Override
    public List<QuizResponse> getAllQuiz() { // 퀴즈 전체 조회
        return quizRepo.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }

    private QuizResponse mapToResponseDto(Quiz quiz) {
        QuizResponse quizResponse = new QuizResponse();

        quizResponse.setQuizId(quiz.getQuizId());
        quizResponse.setOption1(quiz.getOption1());
        quizResponse.setOption2(quiz.getOption2());
        quizResponse.setType(quiz.getType());
        quizResponse.setFrequency(quiz.getFrequency());
        quizResponse.setCreatedAt(quiz.getCreatedAt());
        quizResponse.setUpdatedAt(quiz.getUpdatedAt());

        return quizResponse;
    }
}