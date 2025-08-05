package xyz.catequest.spring.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.catequest.spring.domain.auth.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  boolean existsByUserId(Long userId);

  boolean existsByToken(String token);

  void deleteByUserId(Long userId);
}
