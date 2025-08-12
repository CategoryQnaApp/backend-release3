package xyz.catequest.spring.domain.item.controller;

import io.micrometer.common.lang.Nullable;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.item.dto.response.ItemResponse;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.domain.item.service.ItemService;
import xyz.catequest.spring.global.dto.Response;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  @GetMapping("/v1/items")
  public Response<List<ItemResponse>> getItems(@Nullable @RequestParam ItemType type) {
    List<ItemResponse> response = itemService.getItems(type);
    return Response.success(response);
  }
}
