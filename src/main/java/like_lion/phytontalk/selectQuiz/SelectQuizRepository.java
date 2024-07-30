package like_lion.phytontalk.selectQuiz;

import like_lion.phytontalk.dailyquiz.DailyQuiz;
import like_lion.phytontalk.quiz.Quiz;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SelectQuizRepository extends JpaRepository<SelectQuiz, Long> {
    // 광고, 일반 나눠서 생각해야하고 / (이게 우선순위 젤 높음)선택됐을 때 빈도 증가 하는 로직도 필요함 / 광고 특정빈도 되면 삭제 되는 것도 필요...
    @Query("SELECT sq FROM SelectQuiz sq WHERE sq.dailyQuiz.dailyQuizId = :dailyQuizId ORDER BY sq.quiz.frequency, sq.quiz.createdAt DESC")
    Page<SelectQuiz> findByDailyQuizId(Long dailyQuizId, Pageable pageable);
}