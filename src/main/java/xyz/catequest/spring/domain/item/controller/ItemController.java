package xyz.catequest.spring.domain.item.controller;

import io.micrometer.common.lang.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.item.dto.request.ItemRequest;
import xyz.catequest.spring.domain.item.dto.request.UpdateItemDescriptionRequest;
import xyz.catequest.spring.domain.item.dto.request.UpdateItemNameRequest;
import xyz.catequest.spring.domain.item.dto.request.UpdateItemPriceRequest;
import xyz.catequest.spring.domain.item.dto.response.ItemResponse;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.domain.item.service.ItemService;
import xyz.catequest.spring.global.dto.Response;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {
  private final ItemService itemService;

  // 아이템 저장
  @PostMapping("/v1/items")
  public Response<ItemResponse> saveItem(@Valid @RequestBody ItemRequest request) {
    ItemResponse response = itemService.saveItem(request);
    return Response.success(response);
  }

  // 아이템 수정
  @PutMapping("/v1/items/{itemId}")
  public Response<ItemResponse> updateItem(
      @Positive @PathVariable Long itemId, @Valid @RequestBody ItemRequest request) {
    ItemResponse response = itemService.updateItem(itemId, request);
    return Response.success(response);
  }

  @PatchMapping("/v1/items/{itemId}/name")
  public Response<ItemResponse> updateItemName(
      @Positive @PathVariable Long itemId, @Valid @RequestBody UpdateItemNameRequest request) {
    ItemResponse response = itemService.updateName(itemId, request.getName());
    return Response.success(response);
  }

  @PatchMapping("/v1/items/{itemId}/description")
  public Response<ItemResponse> updateItemDescription(
      @Positive @PathVariable Long itemId,
      @Valid @RequestBody UpdateItemDescriptionRequest request) {
    ItemResponse response = itemService.updateDescription(itemId, request.getDescription());
    return Response.success(response);
  }

  @PatchMapping("/v1/items/{itemId}/price")
  public Response<ItemResponse> updateItemPrice(
      @Positive @PathVariable Long itemId, @Valid @RequestBody UpdateItemPriceRequest request) {
    ItemResponse response = itemService.updatePrice(itemId, request.getPrice());
    return Response.success(response);
  }

  // 아이템 삭제 => 판매만 불가능하게
  @PatchMapping("/v1/items/{itemId}/expired")
  public Response<Void> updateItemExpired(@Positive @PathVariable Long itemId) {
    itemService.expiredItem(itemId);
    return Response.success();
  }

  // 아이템 단일 검색
  @GetMapping("/v1/items/{itemId}")
  public Response<ItemResponse> getItem(@Positive @PathVariable Long itemId) {
    ItemResponse response = itemService.getItem(itemId);
    return Response.success(response);
  }

  // 아이템 목록 검색 => 타입별
  // 아이템 전목록 검색
  @GetMapping("/v1/items")
  public Response<List<ItemResponse>> getItems(@Nullable @RequestParam ItemType type) {
    List<ItemResponse> response = itemService.getItems(type);
    return Response.success(response);
  }
}
