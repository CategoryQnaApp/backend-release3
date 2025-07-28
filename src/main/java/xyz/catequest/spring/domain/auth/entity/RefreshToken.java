package xyz.catequest.spring.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "REFRESH_TOKEN")
public class RefreshToken {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private Long userId;

  @Column(unique = true, nullable = false)
  private String refreshToken;

  public RefreshToken(Long userId, String refreshToken) {
    this.userId = userId;
    this.refreshToken = refreshToken;
  }

  public static RefreshToken of(Long userId, String refreshToken) {
    return new RefreshToken(userId, refreshToken);
  }
}
