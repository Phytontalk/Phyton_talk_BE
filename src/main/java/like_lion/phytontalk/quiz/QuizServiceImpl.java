package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepo;

    @Override
    public QuizResponse createQuiz(QuizRequest quizRequest) { // 퀴즈 생성
        Quiz quiz = new Quiz();

        quiz.setOption1(quizRequest.getOption1());
        quiz.setOption2(quizRequest.getOption2());
        quiz.setType(quizRequest.getType());

        Quiz savedQuiz = quizRepo.save(quiz);   // request로 받은 객체 저장
        return mapToResponseDto(quiz);
    }

    @Override
    public QuizResponse updateQuiz(Long id, QuizRequest quizRequest) { // 퀴즈 수정
        Optional<Quiz> quiz = quizRepo.findById(id);
        if (quiz.isPresent()) {
            Quiz quizToUpdate = quiz.get();

            quizToUpdate.setOption1(quizRequest.getOption1());
            quizToUpdate.setOption2(quizRequest.getOption2());
            quizToUpdate.setType(quizRequest.getType());

            Quiz updatedQuiz = quizRepo.save(quizToUpdate);
            return mapToResponseDto(updatedQuiz);
        } else {
            // 예외처리
            return null;
        }
    }

    @Override
    public void deleteQuiz(Long id) { // 퀴즈 삭제
        if (quizRepo.existsById(id)) {  // 직접적 삭제 안할거면 findById로
            quizRepo.deleteById(id);
        } else {
            // 예외처리
//                throw new ResourceNotFoundException("Quiz not found with id " + id);
            return;
        }
    }


    @Override
    public List<QuizResponse> getAllQuiz() { // 퀴즈 전체 조회
        return quizRepo.findAll().stream().map(this::mapToResponseDto).collect(Collectors.toList());
    }


    @Override
    public List<QuizResponse> getRandomQuiz() { // 퀴즈 오늘의 문제
        return quizRepo.findRandomQuiz().stream().map(this::mapToResponseDto).collect(Collectors.toList());
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