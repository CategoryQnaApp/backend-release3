package xyz.catequest.spring.domain.auth.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.catequest.spring.domain.auth.entity.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

  Optional<RefreshToken> findByRefreshToken(String token);

  boolean existsByUserId(Long userId);

  default void deleteByUserId(Long userId) {
    if( existsByUserId(userId) ) return;
    deleteByUserId(userId);
  }

  boolean existsByRefreshToken(String token);
}
