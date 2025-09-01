package xyz.catequest.spring.domain.bag.dto.response;

import java.math.BigInteger;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.user.entity.User;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CurrenciesResponse {
  private final BigInteger nyang;
  private final BigInteger ticket;

  public static CurrenciesResponse from(User user) {
    return new CurrenciesResponse(user.getMoney(), user.getTicket());
  }
}
