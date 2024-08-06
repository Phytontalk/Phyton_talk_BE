package like_lion.phytontalk.quiz;

import jakarta.persistence.*;
import like_lion.phytontalk.selectQuiz.SelectQuiz;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "quiz")
@Getter
@Setter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 파라미터 받는 생성자
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quiz_id") // 문제 아이디
    private long quizId;

    @Column(name = "option_1", nullable = false) // 선택지1
    private String option1;

    @Column(name = "option_2", nullable = false) // 선택지2
    private String option2;

    @Column(name = "type", nullable = false) // 문제 유형(광고, 일반)
    private String type;

    @Column(name = "frequency", nullable = false) // 문제 출제 빈도
    private int frequency = 0; // 기본값 0으로 설정

    @Column(name = "created_at", nullable = false) // 생성 일자
    private LocalDateTime createdAt;

    @Column(name = "updated_at") // 수정 일자
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "quiz")
    private List<SelectQuiz> selectQuizzes;
}
