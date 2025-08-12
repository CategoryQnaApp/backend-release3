package xyz.catequest.spring.domain.item.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateItemDescriptionRequest {
  @Size(min = 1, max = 255)
  private final String description;
}
