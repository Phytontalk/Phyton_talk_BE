package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.avatar.Avatar;
import like_lion.phytontalk.member.dto.*;
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
        Avatar defaultAvatar = avatarRepository.findById(1L).orElse(null); // ID가 1인 아바타를 기본 아바타로 설정
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

    @Override
    @Transactional
    public MemberInfoResponse getMemberInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        return new MemberInfoResponse(member.getName(), member.getEmail(), member.getSns(), member.getAvatar() != null ? member.getAvatar().getImage() : null);
    }

    @Override
    @Transactional
    public void updateMemberInfo(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.setName(request.name());
        member.setSns(request.sns());

        if (request.avatarId() != null) {
            Avatar avatar = avatarRepository.findById(request.avatarId()).orElse(null);
            member.setAvatar(avatar);
        }

        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void updatePassword(Long memberId, PasswordUpdateRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        member.setPassword(request.password());
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public void deleteMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        memberRepository.delete(member);
    }

    @Override
    @Transactional
    public void verifyPassword(Long memberId, String password) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        checkMemberPassword(member, password);
    }
}
