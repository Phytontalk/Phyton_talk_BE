package like_lion.phytontalk.answer;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.dailyquiz.DailyQuizService;
import like_lion.phytontalk.member.Member;
import like_lion.phytontalk.member.MemberRepository;
import like_lion.phytontalk.quiz.QuizRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;

    private final MemberRepository memberRepository;

    private final QuizRepository quizRepository;
    private final DailyQuizService dailyQuizService;

    @Transactional
    @Override
    public void saveAnswer(Long memberId, AnswerRequset answerRequset) {
        Optional<Member> member = memberRepository.findById(memberId);
        member.orElseThrow(() -> new IllegalArgumentException("멤버가 존재하지 않습니다."));

        DailyQuiz dailyQuiz = dailyQuizService.findByCreatedAt(LocalDateTime.now());
        log.info("저장할떄 아이디" + dailyQuiz.getDailyQuizId());
        Answer answer = Answer.builder().member(member.get()).dailyQuiz(dailyQuiz).build();
        answer.setAnswer(answerRequset.answer());
        answerRepository.save(answer);
    }

    public Answer getTodayAnswer(List<Answer> answerList) {
        if (answerList == null || answerList.isEmpty()) {
            return null;
        }
        for (Answer answer : answerList) {
            // 각 Answer 객체의 createdAt 속성을 가져옴
            DailyQuiz dailyQuiz = dailyQuizService.findByCreatedAt(LocalDateTime.now());
            log.info("----------" + answer.getDailyQuiz().getDailyQuizId() + "  " + dailyQuiz.getDailyQuizId());
            if (answer.getDailyQuiz().getDailyQuizId() == dailyQuiz.getDailyQuizId()) {
                return answer;
            }
        }
        throw new IllegalArgumentException("오늘 날짜의 답안이 없습니다.");
    }
}
