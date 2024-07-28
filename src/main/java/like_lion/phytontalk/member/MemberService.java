package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.member.dto.MemberInfoRequest;
import like_lion.phytontalk.member.dto.MemberUpdateRequest;
import like_lion.phytontalk.member.dto.SignInRequest;
import like_lion.phytontalk.member.dto.SignupRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface MemberService {
    void signUp(SignupRequest request);

    void signIn(SignInRequest request, HttpSession session);

    Optional<Member> findOneMemberByEmail(String email);

    void checkMemberPassword(Member member, String inputPassword);

    Member getMemberInfo(MemberInfoRequest request);

    void updateMemberInfo(Long memberId, MemberUpdateRequest request);

    void deleteMember(MemberInfoRequest request);
}
