package like_lion.phytontalk.member;

import like_lion.phytontalk.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    @Query("SELECT a FROM Answer a WHERE a.member.id = :memberId")
    List<Answer> findAnswersByMemberId(@Param("memberId") Long memberId);
}
