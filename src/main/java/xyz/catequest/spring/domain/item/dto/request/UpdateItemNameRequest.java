package xyz.catequest.spring.domain.item.dto.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateItemNameRequest {
  @Size(min = 1, max = 20)
  private final String name;
}
