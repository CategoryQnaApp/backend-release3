package xyz.catequest.spring.domain.item.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.item.dto.request.ItemRequest;
import xyz.catequest.spring.domain.item.dto.response.ItemResponse;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.domain.item.repository.ItemRepository;
import xyz.catequest.spring.domain.user.service.UserService;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;
  private final UserService userService;

  // 아이템 저장
  @Transactional
  public ItemResponse saveItem(ItemRequest request) {
    Item item = Item.from(request);
    Item save = itemRepository.save(item);
    // todo : 이미지 업로드 및 링크 저장
    return ItemResponse.from(save);
  }

  // 아이템 수정
  @Transactional
  public ItemResponse updateItem(Long itemId, ItemRequest request) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    item.updateName(request.getName());
    item.updateDescription(request.getDescription());
    item.updatePrice(request.getPrice());
    item.updateType(request.getItemType());
    Item update = itemRepository.save(item);
    return ItemResponse.from(update);
  }

  @Transactional
  public ItemResponse updateName(Long itemId, String name) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    item.updateName(name);
    Item update = itemRepository.save(item);
    return ItemResponse.from(update);
  }

  @Transactional
  public ItemResponse updateDescription(Long itemId, String description) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    item.updateDescription(description);
    Item update = itemRepository.save(item);
    return ItemResponse.from(update);
  }

  @Transactional
  public ItemResponse updatePrice(Long itemId, Integer price) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    item.updatePrice(price);
    Item update = itemRepository.save(item);
    return ItemResponse.from(update);
  }

  // 아이템 삭제 => 판매만 불가능하게
  @Transactional
  public void expiredItem(Long itemId) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    item.expiredItem();
  }

  // 아이템 단일 검색
  @Transactional(readOnly = true)
  public ItemResponse getItem(Long itemId) {
    Item item =
        itemRepository
            .findById(itemId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_ITEM));
    return ItemResponse.from(item);
  }

  // 아이템 목록 검색 => 타입별
  // 아이템 전목록 검색
  @Transactional(readOnly = true)
  public List<ItemResponse> getItems(ItemType itemType) {
    if (itemType == null) {
      return itemRepository.findAll().stream().map(ItemResponse::from).toList();
    }
    return itemRepository.findByItemType(itemType).stream().map(ItemResponse::from).toList();
  }
}
