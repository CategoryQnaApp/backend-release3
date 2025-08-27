package xyz.catequest.spring.domain.item.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UpdateItemNameRequest {
  @NotBlank
  @JsonProperty("name")
  @Size(min = 1, max = 20)
  private final String name;
}
