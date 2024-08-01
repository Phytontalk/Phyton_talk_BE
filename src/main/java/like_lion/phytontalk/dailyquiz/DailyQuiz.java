package like_lion.phytontalk.dailyquiz;

import jakarta.persistence.*;
import like_lion.phytontalk.selectQuiz.SelectQuiz;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "daily_quiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_quiz_id") // 오늘의 문제 아이디
    private long dailyQuizId;

    @Column(name = "created_at")    // 생성 일자
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "dailyQuiz")
    private List<SelectQuiz> selectQuizzes;
}
