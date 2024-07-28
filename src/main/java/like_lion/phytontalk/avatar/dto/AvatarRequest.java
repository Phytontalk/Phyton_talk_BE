package like_lion.phytontalk.avatar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvatarRequest {
    private Long avatarId; // 선택할 아바타의 ID
}