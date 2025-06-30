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

    @NotBlank
    @Pattern(regexp = RegularExpression.EMAIL)
    private String email;

    @NotBlank
    private String password;

    @Setter
    @NotBlank
    @Size(min = 1, max = 20)
    private String nickname;

    @Setter
    private String profileImage;

    @Setter
    private Long booksId;
}
