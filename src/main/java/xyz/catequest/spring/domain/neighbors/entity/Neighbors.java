package xyz.catequest.spring.domain.neighbors.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xyz.catequest.spring.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "neighbors")
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Neighbors extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(name = "neighbor_url")
  private String neighborUrl;

  @Column(nullable = false, length = 20)
  private String description;

  // 생성자


  // 행위
}


