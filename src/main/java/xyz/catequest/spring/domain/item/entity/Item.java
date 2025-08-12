package xyz.catequest.spring.domain.item.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.global.entity.BaseEntity;

@Getter
@Entity
@Table(name = "items")
@NoArgsConstructor
public class Item extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private Integer price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ItemType type;
}
