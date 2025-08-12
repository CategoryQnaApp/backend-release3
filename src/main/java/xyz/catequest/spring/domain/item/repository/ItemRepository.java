package xyz.catequest.spring.domain.item.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.item.enums.ItemType;

public interface ItemRepository extends JpaRepository<Item, Long> {
  List<Item> findByItemType(ItemType itemType);
}
