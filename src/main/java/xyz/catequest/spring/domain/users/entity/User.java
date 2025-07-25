package xyz.catequest.spring.domain.users.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.enums.UserRole;
import xyz.catequest.spring.domain.users.enums.UserStatus;
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

  @Column(nullable = false)
  private UserRole role;

  @Column(nullable = false, length = 30)
  private String profileImage;

  @Column(nullable = false)
  private UserStatus status;

  private Long booksId;

  public User(String email, String password, String nickname) {
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    this.status = UserStatus.ACTIVE;
    this.role = UserRole.USER;
    profileImage = "1";
    booksId = 0L;
  }

  public void updateProfileImage(String profileImage) {
    this.profileImage = profileImage;
  }
  public void updateNickname(String nickname) {
    this.nickname = nickname;
  }
  public void updateBooksId(Long booksId) {
    this.booksId = booksId;
  }
  public void updatePassword(String password) {
    this.password = password;
  }

  public static User of(String email, String password, String nickname) {
    return new User(email, password, nickname);
  }

}
