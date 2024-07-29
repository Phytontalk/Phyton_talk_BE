package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.member.dto.*;

import java.util.Optional;

public interface MemberService {
    void signUp(SignupRequest request);

    void signIn(SignInRequest request, HttpSession session);

    Optional<Member> findOneMemberByEmail(String email);

    void checkMemberPassword(Member member, String inputPassword);

    MemberInfoResponse getMemberInfo(Long memberId);

    void updateMemberInfo(Long memberId, MemberUpdateRequest request);

    void updatePassword(Long memberId, PasswordUpdateRequest request);

    void deleteMember(Long memberId);

    void verifyPassword(Long memberId, String password);
}
