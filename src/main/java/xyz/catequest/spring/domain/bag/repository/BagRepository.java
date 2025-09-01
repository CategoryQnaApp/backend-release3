package xyz.catequest.spring.domain.bag.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import xyz.catequest.spring.domain.bag.dto.response.BagResponse;
import xyz.catequest.spring.domain.bag.entity.Bag;

public interface BagRepository extends JpaRepository<Bag, Long> {

  @Query(
      "SELECT new xyz.catequest.spring.domain.bag.dto.response.BagResponse(bag.id, bag.item.id, bag.item.name, bag.item.description, bag.item.price, bag.item.type, bag.buyAt) "
          + "FROM Bag bag "
          + "WHERE bag.user.id =:userId")
  List<BagResponse> getBagResponseList(@Param("userId") Long userId);

  Boolean existsByItem_Id(Long itemId);
}
