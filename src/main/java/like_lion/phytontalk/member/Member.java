package like_lion.phytontalk.member;

import jakarta.persistence.*;
import like_lion.phytontalk.avatar.Avatar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    private String name;

    @Column(length = 40, nullable = false)
    private String email;

    @Column(length = 200, nullable = false)
    private String password;

//    @OneToOne
//    private Avatar avatar;
    public boolean checkPassword(String password){
        return Objects.equals(this.password, password);
    }

}
