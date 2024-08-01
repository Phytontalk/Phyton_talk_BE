package like_lion.phytontalk.dailyquiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DailyQuizRepository extends JpaRepository<DailyQuiz, Long> {
    @Query("SELECT dq FROM DailyQuiz dq WHERE DATE(dq.createdAt) = DATE(:createdAt)")
    DailyQuiz findByCreatedAt(@Param("createdAt") LocalDateTime createdAt);
}