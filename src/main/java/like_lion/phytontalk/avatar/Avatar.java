package like_lion.phytontalk.avatar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatarId;

    @Column(length = 50, nullable = true)
    private String image;

    public Avatar() {
    }
    
    public Avatar(String image) {
        this.image = image;
    }

    // Getter for avatarId
    public Long getAvatarId() {
        return avatarId;
    }

    // Setter for avatarId
    public void setAvatarId(Long avatarId) {
        this.avatarId = avatarId;
    }

    // Getter for image
    public String getImage() {
        return image;
    }

    // Setter for image
    public void setImage(String image) {
        this.image = image;
    }
}