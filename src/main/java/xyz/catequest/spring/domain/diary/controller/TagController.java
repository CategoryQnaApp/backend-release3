package xyz.catequest.spring.domain.diary.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.diary.dto.response.TagResponse;
import xyz.catequest.spring.domain.diary.entity.Tag;
import xyz.catequest.spring.domain.diary.service.TagService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TagController {
  private final TagService tagService;

  @GetMapping("/v1/tags")
  public Response<TagResponse> getTags(@AuthenticationPrincipal AuthUser user) {
    List<Tag> tags = tagService.getTagsByCreator(user.getUserId());
    return Response.success(TagResponse.from(tags));
  }
}
