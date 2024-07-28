package like_lion.phytontalk.avatar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvatarResponse {
    private Long avatarId;
    private String image;
}