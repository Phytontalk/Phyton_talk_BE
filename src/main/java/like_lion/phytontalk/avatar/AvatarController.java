package like_lion.phytontalk.avatar;

import like_lion.phytontalk.avatar.dto.AvatarRequest;
import like_lion.phytontalk.avatar.dto.AvatarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avatars")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AvatarController {
    private final AvatarService avatarService;

    @GetMapping
    public List<AvatarResponse> getAllAvatars() {
        return avatarService.getAllAvatars();
    }

    @PostMapping("/select")
    public AvatarResponse selectAvatar(@RequestBody AvatarRequest avatarRequest) {
        return avatarService.selectAvatar(avatarRequest);
    }
}