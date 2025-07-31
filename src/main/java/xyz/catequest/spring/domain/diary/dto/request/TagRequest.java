package xyz.catequest.spring.domain.diary.dto.request;

import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class TagRequest {
  private final List<String> hashTagList;
}
