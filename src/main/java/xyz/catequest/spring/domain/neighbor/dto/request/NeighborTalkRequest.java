package xyz.catequest.spring.domain.neighbor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NeighborTalkRequest {
  @NotBlank(message = "입력값은 비어 있을 수 없습니다.")
  @Size(max = 255, message = "입력값은 255자를 넘을 수 없습니다.")
  private final String content;
}
