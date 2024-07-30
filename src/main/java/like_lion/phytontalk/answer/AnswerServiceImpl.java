package like_lion.phytontalk.answer;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.member.Member;
import like_lion.phytontalk.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService{

    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

//    private final QuizRepository quizRepository;
//    private final DailyQuizService dailyQuizService;
    @Transactional
    @Override
    public void saveAnswer(Long memberId, AnswerRequset answerRequset) {
        Optional<Member> member = memberRepository.findById(memberId);
        member.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다."));

        DailyQuiz dailyQuiz = dailyQuizService.findDailyQuiz();

        Answer answer = Answer.builder().member(member.get()).dailyQuiz(dailyQuiz).build();
        answer.setAnswer(answerRequset.answer());
        answerRepository.save(answer);
    }
}
