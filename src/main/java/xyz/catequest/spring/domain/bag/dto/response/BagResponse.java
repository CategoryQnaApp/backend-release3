package xyz.catequest.spring.domain.bag.dto.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.item.enums.ItemType;

@Getter
@RequiredArgsConstructor
public class BagResponse {
  private final Long id;
  private final Long itemId;
  private final String name;
  private final String description;
  private final Integer price;
  private final ItemType type;
  private final LocalDateTime buyAt;
}
