package like_lion.phytontalk.avatar;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Avatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long avatarId;

    @Column(length = 50, nullable = true)
    private String image;

    // Custom constructor for image field
    public Avatar(String image) {
        this.image = image;
    }
}