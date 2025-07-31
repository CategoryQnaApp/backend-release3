package xyz.catequest.spring.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @Column(unique = true, nullable = false)
  private String token;

  public RefreshToken(User user, String refreshToken) {
    this.user = user;
    this.token = refreshToken;
  }

  public static RefreshToken of(User user, String refreshToken) {
    return new RefreshToken(user, refreshToken);
  }
}
