package like_lion.phytontalk.selectQuiz;

import jakarta.persistence.*;
import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.quiz.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SelectQuiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SelectQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "select_quiz_id") // 질문 할당 아이디
    private long selectQuizId;

    @ManyToOne
    @JoinColumn(name = "daily_quiz_id") // 오늘의 문제 아이디(FK)
    private DailyQuiz dailyQuiz;

    @ManyToOne
    @JoinColumn(name = "quiz_id")   // 문제 아이디(FK)
    private Quiz quiz;
}
