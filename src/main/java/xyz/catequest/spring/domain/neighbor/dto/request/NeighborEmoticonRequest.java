package xyz.catequest.spring.domain.neighbor.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class NeighborEmoticonRequest {
  @NotBlank(message = "입력값은 비어 있을 수 없습니다.")
  @Size(max = 20, message = "입력값은 20자를 넘을 수 없습니다.")
  private final String name;

  @NotNull private final MultipartFile emoticonImage;
}
