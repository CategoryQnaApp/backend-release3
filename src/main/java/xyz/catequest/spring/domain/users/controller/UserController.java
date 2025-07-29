package xyz.catequest.spring.domain.users.controller;

import jakarta.validation.Valid;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.catequest.spring.domain.users.dto.request.DeleteUserRequest;
import xyz.catequest.spring.domain.users.dto.request.UpdateUserNicknameRequest;
import xyz.catequest.spring.domain.users.dto.request.UpdateUserPasswordRequest;
import xyz.catequest.spring.domain.users.dto.response.GetUserResponse;
import xyz.catequest.spring.domain.users.service.UserService;
import xyz.catequest.spring.global.dto.Response;
import xyz.catequest.spring.global.entity.AuthUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @GetMapping("/v1/users")
  public Response<GetUserResponse> myInfo(@AuthenticationPrincipal AuthUser authUser) {
    return Response.success(userService.myInfo(authUser.getUserId()));
  }

  @PatchMapping("/v1/users/profileImages")
  public Response<Void> updateProfileImage(
      @AuthenticationPrincipal AuthUser authUser, @Valid @RequestPart MultipartFile profileImage)
      throws IOException {
    // todo : images 검사 로직 추가
    userService.updateProfileImage(authUser.getUserId(), profileImage.getBytes());
    return Response.success();
  }

  @PatchMapping("/v1/users/nickname")
  public Response<Void> updateNickname(
      @AuthenticationPrincipal AuthUser authUser,
      @Valid @RequestBody UpdateUserNicknameRequest request) {
    userService.updateNickname(authUser.getUserId(), request.getNickname());
    return Response.success();
  }

  @PatchMapping("/v1/users/password")
  public Response<Void> updatePassword(
      @AuthenticationPrincipal AuthUser authUser,
      @Valid @RequestBody UpdateUserPasswordRequest request) {
    userService.updatePassword(
        authUser.getUserId(), request.getOldPassword(), request.getNewPassword());
    return Response.success();
  }

  @PatchMapping("/v1/users/bookId/{bookId}")
  public Response<Void> updateBook(
      @AuthenticationPrincipal AuthUser authUser, @PathVariable Long bookId) {
    userService.updateBookId(authUser.getUserId(), bookId);
    return Response.success();
  }

  @PostMapping("/v1/users/delete")
  public Response<Void> deleteUsers(
      @AuthenticationPrincipal AuthUser authUser, @RequestBody DeleteUserRequest request) {
    userService.deleteUser(authUser.getUserId(), request.getPassword());
    return Response.success();
  }
}
