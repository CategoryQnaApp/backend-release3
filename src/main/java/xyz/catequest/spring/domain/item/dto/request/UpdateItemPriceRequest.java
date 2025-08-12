package xyz.catequest.spring.domain.item.dto.request;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateItemPriceRequest {
  @PositiveOrZero private final Integer price;
}
