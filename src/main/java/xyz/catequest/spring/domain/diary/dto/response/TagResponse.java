package xyz.catequest.spring.domain.diary.dto.response;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.diary.entity.Tag;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class TagResponse {
  private final List<String> tags;

  public static TagResponse from(List<Tag> tags) {
    return TagResponse.of(tags.stream().map(Tag::getName).toList());
  }
}
