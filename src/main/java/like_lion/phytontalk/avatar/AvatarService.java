package like_lion.phytontalk.avatar;

import like_lion.phytontalk.avatar.dto.AvatarRequest;
import like_lion.phytontalk.avatar.dto.AvatarResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AvatarService {
    private final AvatarRepository avatarRepository;

    public List<AvatarResponse> getAllAvatars() {
        return avatarRepository.findAll().stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    public AvatarResponse selectAvatar(AvatarRequest avatarRequest) {
        Optional<Avatar> avatarOptional = avatarRepository.findById(avatarRequest.getAvatarId());
        if (avatarOptional.isPresent()) {
            return mapToResponseDto(avatarOptional.get());
        } else {
            throw new IllegalArgumentException("Invalid avatar ID");
        }
    }

    private AvatarResponse mapToResponseDto(Avatar avatar) {
        return new AvatarResponse(
                avatar.getAvatarId(),
                avatar.getImage()
        );
    }
}