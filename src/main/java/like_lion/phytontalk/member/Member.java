package like_lion.phytontalk.member;

import jakarta.persistence.*;
import like_lion.phytontalk.answer.Answer;
import like_lion.phytontalk.avatar.Avatar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
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

    @Column(length = 200, nullable = true)
    private String sns;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "avatarId")
    private Avatar avatar;

//    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Answer> answer;
    public boolean checkPassword(String password){
        return Objects.equals(this.password, password);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSns(String sns) {
        this.sns = sns;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
}
