package xyz.catequest.spring.domain.item.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.item.enums.ItemType;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class ItemResponse {
  private final Long id;
  private final String name;
  private final String description;
  private final ItemType type;
  private final LocalDateTime createdAt;

  public static ItemResponse from(Item item) {
    return new ItemResponse(
        item.getId(), item.getName(), item.getDescription(), item.getType(), item.getCreatedAt());
  }
}
