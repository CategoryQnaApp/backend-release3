package xyz.catequest.spring.domain.users.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.catequest.spring.global.Regexp.RegularExpression;
import xyz.catequest.spring.global.entity.BaseEntity;


@Getter
@NoArgsConstructor
@Entity
@Table(name = "USERS")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    @Column(nullable = false, length = 20)
    private String nickname;

    @Column(nullable = false, length = 30)
    private String profileImage;

    private Long booksId;

    public void updateProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateBooksId(Long booksId) {
        this.booksId = booksId;
    }
}
