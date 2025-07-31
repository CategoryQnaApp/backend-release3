package xyz.catequest.spring.domain.diary.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagRequest {
  @Size(max = 5)
  @Valid
  @NotEmpty
  private final List<String> tagList;
}
