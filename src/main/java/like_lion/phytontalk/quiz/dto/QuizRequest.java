package like_lion.phytontalk.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Getter

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizRequest {
    private String option1;
    private String option2;
    private String type;
    // 빈도, 수정일자 등은 서버에서 관리
}
