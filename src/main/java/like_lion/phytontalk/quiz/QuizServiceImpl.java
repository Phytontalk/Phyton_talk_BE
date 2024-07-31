package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepo;

    @Override
    @Transactional
    public QuizResponse createQuiz(QuizRequest quizRequest) { // 퀴즈 생성
        Quiz quiz = new Quiz();
        Timestamp currentTime = Timestamp.from(Instant.now());

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
        Timestamp currentTime = Timestamp.from(Instant.now());

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

        return mapToResponseDto(findQuiz);
    }

    @Override
    @Transactional
    public void deleteQuiz(Long id) { // 퀴즈 삭제
        Optional<Quiz> quiz = quizRepo.findById(id);
        quiz.orElseThrow(()-> new RuntimeException("Quiz not found"));
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