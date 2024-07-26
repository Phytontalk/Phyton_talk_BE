package like_lion.phytontalk.quiz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private long quizId;
    private String option1;
    private String option2;
    private String type;
    private int frequency;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
