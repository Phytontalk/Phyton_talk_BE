package like_lion.phytontalk.quiz.dto;

import lombok.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResponse {
    private long quizId;
    private String option1;
    private String option2;
    private String type;
    private int frequency;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
