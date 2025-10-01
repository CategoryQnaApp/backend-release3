package xyz.catequest.spring.domain.neighbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.neighbor.entity.Neighbor;

public interface NeighborRepository extends JpaRepository<Neighbor, Long> {}
