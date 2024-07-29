package like_lion.phytontalk.dailyquiz;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "DailyQuiz")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyQuiz {
    // 정확히 오늘 날짜의 오늘의 퀴즈 반환 서비스 코드
    // findby

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_quiz_id") // 오늘의 문제 아이디
    private long dailyQuizId;

    @Column(name = "created_at")    // 생성 일자
    private Timestamp createdAt;

    //@ManyToOne
}
