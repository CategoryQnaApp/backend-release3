package xyz.catequest.spring.domain.users.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import xyz.catequest.spring.domain.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByEmail(String email);
  boolean existsByEmail(String email);
  default void delete(User user) {
    throw new UnsupportedOperationException("Soft delete is used instead of physical delete");
  }
}
