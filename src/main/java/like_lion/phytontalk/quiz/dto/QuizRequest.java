package like_lion.phytontalk.quiz.dto;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {
    private String option1;
    private String option2;
    private String type;
    // 빈도, 수정일자 등은 서버에서 관리
}
