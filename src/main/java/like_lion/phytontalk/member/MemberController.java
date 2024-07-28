package like_lion.phytontalk.member;

import jakarta.servlet.http.HttpSession;
import like_lion.phytontalk.member.dto.MemberInfoRequest;
import like_lion.phytontalk.member.dto.MemberUpdateRequest;
import like_lion.phytontalk.member.dto.SignInRequest;
import like_lion.phytontalk.member.dto.SignupRequest;
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
    @GetMapping("/member")
    public ResponseEntity<Member> getMemberInfo(@RequestParam String email, @RequestParam String password){
        MemberInfoRequest request = new MemberInfoRequest(email, password);
        Member member = memberServiceImpl.getMemberInfo(request);
        return ResponseEntity.status(HttpStatus.OK).body(member);
    }

    // 사용자 정보 변경 요청 (PUT)
    @PutMapping("/member")
    public ResponseEntity<String> updateMemberInfo(@RequestBody MemberUpdateRequest request, @RequestParam String currentPassword, HttpSession session){
        Member member = (Member) session.getAttribute("member");
        if (member == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }
        memberServiceImpl.checkMemberPassword(member, currentPassword);
        memberServiceImpl.updateMemberInfo(member.getId(), request);
        return ResponseEntity.status(HttpStatus.OK).body("회원정보 수정됨");
    }

    // 탈퇴 (DELETE)
    @DeleteMapping("/member")
    public ResponseEntity<String> deleteMember(@RequestBody MemberInfoRequest request, HttpSession session){
        memberServiceImpl.checkMemberPassword(memberServiceImpl.getMemberInfo(request), request.password());
        memberServiceImpl.deleteMember(request);
        session.invalidate();
        return ResponseEntity.status(HttpStatus.OK).body("회원 탈퇴됨");
    }

}
