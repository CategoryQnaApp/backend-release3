package xyz.catequest.spring.domain.bag.service;

import java.math.BigInteger;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.bag.dto.response.BagResponse;
import xyz.catequest.spring.domain.bag.dto.response.CurrenciesResponse;
import xyz.catequest.spring.domain.bag.entity.Bag;
import xyz.catequest.spring.domain.bag.repository.BagRepository;
import xyz.catequest.spring.domain.item.entity.Item;
import xyz.catequest.spring.domain.item.service.ItemService;
import xyz.catequest.spring.domain.user.entity.User;
import xyz.catequest.spring.domain.user.service.UserService;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;

@Service
@RequiredArgsConstructor
public class BagService {
  private final BagRepository bagRepository;
  private final UserService userService;
  private final ItemService itemService;

  @Transactional(readOnly = true)
  public CurrenciesResponse getCurrencies(Long userId) {
    User user = userService.getUserEntity(userId);
    return CurrenciesResponse.from(user);
  }

  @Transactional(readOnly = true)
  public List<BagResponse> getBags(Long userId) {
    return bagRepository.getBagResponseList(userId);
  }

  @Transactional
  public void payments(Long userId, Long itemId) {
    if (bagRepository.existsByItem_IdAndUser_Id(itemId, userId)) {
      throw new InvalidRequestException(ErrorMessage.ALREADY_THERE);
    }
    User user = userService.getUserEntity(userId);
    Item item = itemService.getItemEntity(itemId);
    user.countMoney(BigInteger.valueOf(item.getPrice()));
    Bag bag = Bag.buy(item, user);
    bagRepository.save(bag);
  }
}
