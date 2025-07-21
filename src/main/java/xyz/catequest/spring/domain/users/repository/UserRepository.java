package xyz.catequest.spring.domain.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.users.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
