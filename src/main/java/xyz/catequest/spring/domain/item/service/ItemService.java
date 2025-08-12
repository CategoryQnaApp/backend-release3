package xyz.catequest.spring.domain.item.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.item.dto.response.ItemResponse;
import xyz.catequest.spring.domain.item.enums.ItemType;
import xyz.catequest.spring.domain.item.repository.ItemRepository;

@Service
@RequiredArgsConstructor
public class ItemService {
  private final ItemRepository itemRepository;

  @Transactional(readOnly = true)
  public List<ItemResponse> getItems(ItemType itemType) {
    if (itemType == null) {
      return itemRepository.findAll().stream().map(ItemResponse::from).toList();
    }
    return itemRepository.findByItemType(itemType).stream().map(ItemResponse::from).toList();
  }
}
