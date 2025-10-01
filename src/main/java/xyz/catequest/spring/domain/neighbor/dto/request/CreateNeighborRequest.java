package xyz.catequest.spring.domain.neighbor.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@RequiredArgsConstructor
public class CreateNeighborRequest {
  @NotBlank
  @Size(max = 20)
  private final String name;

  @NotNull private final MultipartFile neighborImage;

  @Size(max = 255)
  private final String description;

  // todo : Talk, Emoticon dto내용추가
  @Valid @NotNull private final List<NeighborTalkRequest> talks;
  @Valid @NotNull private final List<NeighborEmoticonRequest> emoticons;
}
