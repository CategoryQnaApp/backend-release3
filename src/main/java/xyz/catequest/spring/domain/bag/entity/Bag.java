package xyz.catequest.spring.domain.bag.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.user.entity.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "bags",
    uniqueConstraints = {
      @UniqueConstraint(
          name = "uk_bag_user_item",
          columnNames = {"user_id", "item_id"})
    },
    indexes = {
      @Index(name = "idx_bag_user", columnList = "user_id"),
      @Index(name = "idx_bag_item", columnList = "item_id")
    })
public class Bag {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, name = "buy_at")
  private LocalDateTime buyAt;

  @Column(nullable = false)
  private Integer quantity;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private Bag(Item item, User user) {
    this.item = item;
    this.user = user;
    quantity = 1;
    buyAt = LocalDateTime.now();
  }

  public static Bag buy(Item item, User user) {
    return new Bag(item, user);
  }
}
