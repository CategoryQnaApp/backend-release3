package xyz.catequest.spring.domain.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.auth.entity.EmailAuth;

public interface EmailAuthRepository extends JpaRepository<EmailAuth, Long> {
  Optional<EmailAuth> findByEmail(String email);

  boolean existsByEmail(String email);
}
