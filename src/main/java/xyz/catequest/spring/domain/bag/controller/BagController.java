package xyz.catequest.spring.domain.bag.controller;

import jakarta.validation.constraints.Positive;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.bag.dto.response.BagResponse;
import xyz.catequest.spring.domain.bag.dto.response.CurrenciesResponse;
import xyz.catequest.spring.domain.bag.service.BagService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BagController {

  private final BagService bagService;

  @GetMapping("/v1/users/bags")
  public Response<List<BagResponse>> getBags(@AuthenticationPrincipal AuthUser user) {
    return Response.success(bagService.getBags(user.getUserId()));
  }

  @GetMapping("/v1/users/bags/currencies")
  public Response<CurrenciesResponse> getCurrencies(@AuthenticationPrincipal AuthUser user) {
    return Response.success(bagService.getCurrencies(user.getUserId()));
  }

  @PostMapping("/v1/users/bags/items/{itemId}")
  public Response<Void> payments(
      @AuthenticationPrincipal AuthUser user, @Positive @PathVariable Long itemId) {
    bagService.payments(user.getUserId(), itemId);
    return Response.noContent();
  }
}
