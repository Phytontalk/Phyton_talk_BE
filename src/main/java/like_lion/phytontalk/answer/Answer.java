package like_lion.phytontalk.answer;

import jakarta.persistence.*;
import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.member.Member;
import like_lion.phytontalk.quiz.Quiz;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private DailyQuiz dailyQuiz;

    @Column(length = 1, nullable = false)
    private String answer1;

    @Column(length = 1, nullable = false)
    private String answer2;

    @Column(length = 1, nullable = false)
    private String answer3;

    @Column(length = 1, nullable = false)
    private String answer4;

    @Column(length = 1, nullable = false)
    private String answer5;

    @Column(length = 1, nullable = false)
    private String answer6;

    @Column(length = 1, nullable = false)
    private String answer7;

    @Column(length = 1, nullable = false)
    private String answer8;

    @Column(length = 1, nullable = false)
    private String answer9;

    @Column(length = 1, nullable = false)
    private String answer10;

    void setAnswer(List<Integer> answer){
        if (answer == null || answer.size() != 10)
            throw new IllegalArgumentException("Exactly 10 answers are required.");
        answer1 = answer.get(0).toString();
        answer2 = answer.get(1).toString();
        answer3 = answer.get(2).toString();
        answer4 = answer.get(3).toString();
        answer5 = answer.get(4).toString();
        answer6 = answer.get(5).toString();
        answer7 = answer.get(6).toString();
        answer8 = answer.get(7).toString();
        answer9 = answer.get(8).toString();
        answer10 = answer.get(9).toString();
    }
}
