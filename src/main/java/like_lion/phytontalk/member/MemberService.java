package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.member.dto.SignInRequest;
import like_lion.phytontalk.member.dto.SignupRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {
    void signUp(SignupRequest request);

    void signIn(SignInRequest request, HttpSession session);

    Optional<Member> findOneMemberByEmail(String email);

    void checkMemberPassword(Member member, String inputPassword);
}
