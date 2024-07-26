package like_lion.phytontalk.quiz;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Entity
@Table(name = "Quiz")
@Data   // getter setter 자동 생성
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 파라미터 받는 생성자
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id") // 퀴즈 아이디
    private long quizId;

    @Column(name = "option_1", nullable = false) // 선택지1
    private String option1;

    @Column(name = "option_2", nullable = false) // 선택지2
    private String option2;

    @Column(name = "type", nullable = false) // 퀴즈 유형(광고, 일반)
    private String type;

    @Column(name = "frequency", nullable = false) // 퀴즈 출제 빈도
    private int frequency = 0; // 기본값 0으로 설정

    @Column(name = "created_at", nullable = false) // 생성 일자
    private Timestamp createdAt;

    @Column(name = "updated_at") // 수정 일자
    private Timestamp updatedAt;
}