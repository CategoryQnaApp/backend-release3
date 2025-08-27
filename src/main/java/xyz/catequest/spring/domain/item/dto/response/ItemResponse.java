package xyz.catequest.spring.domain.item.dto.response;

import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.global.enums.ErrorMessage;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ItemResponse {
  private final Long id;
  private final String name;
  private final String description;
  private final Integer price;
  private final ItemType type;
  private final LocalDateTime createdAt;

  public static ItemResponse from(Item item) {
    Objects.requireNonNull(item, ErrorMessage.NOT_FOUND_ITEM.getMessage());
    return ItemResponse.of(
        item.getId(),
        item.getName(),
        item.getDescription(),
        item.getPrice(),
        item.getType(),
        item.getCreatedAt());
  }
}
