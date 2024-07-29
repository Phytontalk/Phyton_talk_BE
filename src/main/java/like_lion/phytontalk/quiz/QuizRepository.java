package like_lion.phytontalk.quiz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
//    List<Quiz> findAll(Pageable pageable);
}
