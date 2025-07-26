package xyz.catequest.spring.domain.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.auth.Entity.Email;

public interface EmailAuthRepository extends JpaRepository<Email, Long> {
  Optional<Email> findByEmail(String email);

  boolean existsByEmail(String email);
}
