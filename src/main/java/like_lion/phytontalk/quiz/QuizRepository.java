package like_lion.phytontalk.quiz;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    // 랜덤 기준? 설정 필요
    @Query("SELECT q FROM Quiz q ORDER BY q.frequency, q.createdAt DESC")
    List<Quiz> findDailyQuiz(Pageable pageable);
//    List<Quiz> findAll(Pageable pageable);
}
