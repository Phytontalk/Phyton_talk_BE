package like_lion.phytontalk.quiz;

import like_lion.phytontalk.quiz.dto.QuizRequest;
import like_lion.phytontalk.quiz.dto.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepo;

    @Override
    public QuizResponse createQuiz(QuizRequest quizRequest) { // 퀴즈 생성
        Quiz quiz = new Quiz();
        // 예시
//        quiz.setQuestion(quizRequestDto.getQuestion());
//        quiz.setAnswer(quizRequestDto.getAnswer());
//        Quiz savedQuiz = quizRepository.save(quiz);
        return mapToResponseDto(quiz);
    }

    @Override
    public QuizResponse updateQuiz(Long id, QuizRequest quizRequest) { // 퀴즈 수정
        Optional<Quiz> quiz = quizRepo.findById(id);
            if (quiz.isPresent()) {
                Quiz quizToUpdate = quiz.get();
                // 기능
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
//                quizRepo.deleteById(id);
            } else {
            // 예외처리
//                throw new ResourceNotFoundException("Quiz not found with id " + id);
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
        // setValue
        return quizResponse;
    }
}
