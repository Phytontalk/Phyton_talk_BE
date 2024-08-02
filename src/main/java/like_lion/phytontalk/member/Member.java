package like_lion.phytontalk.member;

import jakarta.persistence.*;
import like_lion.phytontalk.answer.Answer;
import like_lion.phytontalk.avatar.Avatar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
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

    @Column(length = 200, nullable = false)
    private String sns;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id", referencedColumnName = "avatarId")
    private Avatar avatar;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Answer> answers;

    @Column(nullable = false)
    private LocalDate birthDate;

    public boolean checkPassword(String password) {
        return Objects.equals(this.password, password);
    }

    public int getBirthYear() {
        return birthDate.getYear();
    }

}