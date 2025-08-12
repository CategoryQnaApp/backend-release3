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
import xyz.catequest.spring.domain.item.dto.request.ItemRequest;
import xyz.catequest.spring.domain.item.enums.ItemStatus;
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

  private String imageUrl;

  @Column(nullable = false)
  private Integer price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ItemType type;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private ItemStatus status;

  public void updateName(String newName) {
    this.name = newName;
  }

  public void updateDescription(String newDescription) {
    this.description = newDescription;
  }

  public void updatePrice(Integer newPrice) {
    this.price = newPrice;
  }

  public void updateType(ItemType newType) {
    this.type = newType;
  }

  public void expiredItem() {
    this.status = ItemStatus.EXPIRED;
    softDelete();
  }

  public void updateImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Item(String name, String description, Integer price, ItemType type) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.type = type;
    this.status = ItemStatus.SELL;
  }

  public static Item of(String name, String description, Integer price, ItemType type) {
    return new Item(name, description, price, type);
  }

  public static Item from(ItemRequest item) {
    return new Item(item.getName(), item.getDescription(), item.getPrice(), item.getItemType());
  }
}
