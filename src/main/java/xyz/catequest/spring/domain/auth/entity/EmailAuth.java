package xyz.catequest.spring.domain.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.auth.enums.EmailStatus;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "EMAIL_AUTH")
public class EmailAuth {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true, nullable = false)
  private String email;

  private String verificationCode;

  private EmailStatus emailStatus;

  public EmailAuth(String email, String verificationCode) {
    this.email = email;
    this.emailStatus = EmailStatus.PENDING_VERIFICATION;
    this.verificationCode = verificationCode;
  }

  public static EmailAuth of(String email, String verificationCode) {
    return new EmailAuth(email, verificationCode);
  }

  public void markAsVerified() {
    this.emailStatus = EmailStatus.VERIFIED;
  }
}
