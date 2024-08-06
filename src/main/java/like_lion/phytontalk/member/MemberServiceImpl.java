package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.answer.Answer;
import like_lion.phytontalk.avatar.Avatar;
import like_lion.phytontalk.avatar.AvatarRepository;
import like_lion.phytontalk.friend.dto.FriendListResponse;
import like_lion.phytontalk.friend.dto.FriendResponse;
import like_lion.phytontalk.member.dto.*;
import like_lion.phytontalk.quiz.Quiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AvatarRepository avatarRepository;
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
                .avatar(defaultAvatar)
                .sns(request.sns())
                .birthYear(request.birthYear())
                .build();
        memberRepository.save(member);
    }

    @Override
    @Transactional
    public Member signIn(SignInRequest request, HttpSession session) {
        Optional<Member> loginRequestMember = findOneMemberByEmail(request.email());
        Member signedInMember = loginRequestMember.orElseThrow(() -> new IllegalArgumentException("가입되어 있지 않은 회원입니다."));
        checkMemberPassword(signedInMember, request.password());
        session.setAttribute("member", signedInMember); //세션에 멤버 셋팅
        return signedInMember;
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
        return new MemberInfoResponse(
                member.getName(),
                member.getEmail(),
                member.getSns(),
                member.getAvatar() != null ? member.getAvatar().getAvatarId() : null,
                member.getBirthYear());
    }

    @Override
    @Transactional
    public void updateMemberInfo(Long memberId, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        if(request.name() != null) {
            member.setName(request.name());
        }

        if(request.sns() != null) {
            member.setName(request.sns());
        }

        if (request.avatarId() != null) {
            Avatar avatar = avatarRepository.findById(request.avatarId()).orElse(null);
            log.info("avatar {}", avatar);
            member.setAvatar(avatar);
        }

        log.info("updating member info {}", request.avatarId());

        memberRepository.save(member);
        log.info("updated {}", member.getAvatar());
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

    @Override
    @Transactional
    public FriendListResponse getRecommendedFriends(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));

        List<Answer> memberAnswers = memberRepository.findAnswersByMemberId(memberId);
        List<Member> allMembers = memberRepository.findAll();

        Member mostSimilar = null;
        Member leastSimilar = null;
        int maxMatchCount = -1;
        int minMatchCount = Integer.MAX_VALUE;

        for (Member friend : allMembers) {
            if (friend.getId().equals(memberId)) {
                continue; // 자기 자신은 제외
            }

            List<Answer> friendAnswers = memberRepository.findAnswersByMemberId(friend.getId());
            int matchCount = calculateMatchCount(memberAnswers, friendAnswers);

            if (matchCount > maxMatchCount) {
                maxMatchCount = matchCount;
                mostSimilar = friend;
            }

            if (matchCount < minMatchCount) {
                minMatchCount = matchCount;
                leastSimilar = friend;
            }
        }

        FriendResponse mostSimilarResponse = createFriendResponse(mostSimilar, "good");
        FriendResponse leastSimilarResponse = createFriendResponse(leastSimilar, "bad");

        List<FriendResponse> friendResponses = List.of(mostSimilarResponse, leastSimilarResponse);
        return new FriendListResponse(friendResponses);
    }

    private int calculateMatchCount(List<Answer> memberAnswers, List<Answer> friendAnswers) {
        int matchCount = 0;
        for (int i = 0; i < memberAnswers.size(); i++) {
            Answer memberAnswer = memberAnswers.get(i);
            Answer friendAnswer = friendAnswers.get(i);

            // 각 답변의 10개의 항목을 비교하여 일치하는 수를 계산
            if (memberAnswer.getAnswer1().equals(friendAnswer.getAnswer1())) matchCount++;
            if (memberAnswer.getAnswer2().equals(friendAnswer.getAnswer2())) matchCount++;
            if (memberAnswer.getAnswer3().equals(friendAnswer.getAnswer3())) matchCount++;
            if (memberAnswer.getAnswer4().equals(friendAnswer.getAnswer4())) matchCount++;
            if (memberAnswer.getAnswer5().equals(friendAnswer.getAnswer5())) matchCount++;
            if (memberAnswer.getAnswer6().equals(friendAnswer.getAnswer6())) matchCount++;
            if (memberAnswer.getAnswer7().equals(friendAnswer.getAnswer7())) matchCount++;
            if (memberAnswer.getAnswer8().equals(friendAnswer.getAnswer8())) matchCount++;
            if (memberAnswer.getAnswer9().equals(friendAnswer.getAnswer9())) matchCount++;
            if (memberAnswer.getAnswer10().equals(friendAnswer.getAnswer10())) matchCount++;
        }
        return matchCount;
    }

    private FriendResponse createFriendResponse(Member friend, String type) {
        Long avatarId = friend.getAvatar() != null ? friend.getAvatar().getAvatarId() : null;

        return new FriendResponse(
                friend.getId(),
                friend.getName(),
                friend.getSns(),
                avatarId,
                type,
                friend.getBirthYear() // 생년 반환
        );
    }
}
