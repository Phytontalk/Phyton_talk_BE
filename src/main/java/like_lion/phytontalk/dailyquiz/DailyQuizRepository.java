package like_lion.phytontalk.dailyquiz;

import like_lion.phytontalk.selectQuiz.SelectQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface DailyQuizRepository extends JpaRepository<DailyQuiz, Long> {
    @Query("SELECT dq FROM DailyQuiz dq WHERE DATE(dq.createdAt) = DATE(:createdAt)")
    DailyQuiz findByCreatedAt(@Param("date") Timestamp createdAt);
}
