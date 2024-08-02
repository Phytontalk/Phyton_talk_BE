package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.friend.dto.FriendListResponse;
import like_lion.phytontalk.member.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody SignupRequest request){
        memberServiceImpl.signUp(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입됨");
    }

    @PostMapping("/login")
    public ResponseEntity<String> signIn(@RequestBody SignInRequest request, HttpSession session){
        memberServiceImpl.signIn(request, session);
        return ResponseEntity.status(HttpStatus.OK).body("로그인 성공");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> signOut(HttpSession session){
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("로그아웃");
    }

    // 사용자 정보 조회 (GET)
    @GetMapping("/member/{memberId}")
    public ResponseEntity<MemberInfoResponse> getMemberInfo(@PathVariable Long memberId){
        MemberInfoResponse member = memberServiceImpl.getMemberInfo(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    @PutMapping("/member/{memberId}")
    public ResponseEntity<String> updateMemberInfo(@PathVariable Long memberId, @RequestBody MemberUpdateRequest request){
        memberServiceImpl.updateMemberInfo(memberId, request);
        return ResponseEntity.status(HttpStatus.OK).body("회원정보 수정됨");
    }

    @PutMapping("/member/{memberId}/password")
    public ResponseEntity<String> updatePassword(@PathVariable Long memberId, @RequestBody PasswordUpdateRequest request){
        memberServiceImpl.updatePassword(memberId, request);
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 수정됨");
    }

    @PostMapping("/member/{memberId}/password")
    public ResponseEntity<String> verifyPassword(@PathVariable Long memberId, @RequestBody PasswordUpdateRequest request){
        memberServiceImpl.verifyPassword(memberId, request.password());
        return ResponseEntity.status(HttpStatus.OK).body("비밀번호 확인됨");
    }

    @DeleteMapping("/member/{memberId}")
    public ResponseEntity<String> deleteMember(@PathVariable Long memberId){
        memberServiceImpl.deleteMember(memberId);
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴됨");
    }

    @GetMapping("/friend/{memberId}")
    public ResponseEntity<FriendListResponse> getRecommendedFriends(@PathVariable Long memberId) {
        FriendListResponse friends = memberServiceImpl.getRecommendedFriends(memberId);
        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }
}
