package like_lion.phytontalk.selectQuiz;

import java.util.List;

public record DailyQuizResponse(List<Question> questions) {

    public static record Question(Long questionId, List<String> question){

    }

}
