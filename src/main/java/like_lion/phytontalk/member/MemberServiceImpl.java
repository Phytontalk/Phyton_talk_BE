package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.member.dto.SignInRequest;
import like_lion.phytontalk.member.dto.SignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public void signUp(SignupRequest request) {
        if (findOneMemberByEmail(request.email()).isPresent())
            throw new IllegalArgumentException("이미 가입된 이메일입니다.");
        Member member = Member.builder()
                .email(request.email())
                .name(request.name())
                .password(request.password())
                .build();
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void signIn(SignInRequest request, HttpSession session) {
        Optional<Member> loginRequestMember = findOneMemberByEmail(request.email());
        Member signedInMember = loginRequestMember.orElseThrow(() -> new IllegalArgumentException("가입되어 있지 않은 회원입니다."));
        checkMemberPassword(signedInMember, request.password());
        session.setAttribute("member", signedInMember); //세션에 멤버 셋팅
    }

    @Override
    @Transactional
    public Optional<Member> findOneMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void checkMemberPassword(Member member, String inputPassword) {
        if (!member.checkPassword(inputPassword)) {
            throw new IllegalArgumentException("비밀번호 불일치");
        }
    }
}
