package xyz.catequest.spring.domain.item.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.item.enums.ItemType;

@Getter
@RequiredArgsConstructor
public class ItemRequest {
  @Size(min = 1, max = 20)
  private final String name;

  @Size(min = 1, max = 255)
  private final String description;

  @PositiveOrZero private final Integer price;
  @NotNull private final ItemType itemType;
}
