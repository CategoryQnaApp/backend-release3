package xyz.catequest.spring.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.catequest.spring.domain.auth.dto.request.EmailRequest;
import xyz.catequest.spring.domain.auth.dto.request.EmailVerificationRequest;
import xyz.catequest.spring.domain.auth.dto.request.SignInAuthRequest;
import xyz.catequest.spring.domain.auth.dto.request.SignUpAuthRequest;
import xyz.catequest.spring.domain.auth.dto.response.EmailVerificationResponse;
import xyz.catequest.spring.domain.auth.dto.response.SignAuthResponse;
import xyz.catequest.spring.domain.auth.service.AuthService;
import xyz.catequest.spring.global.dto.Response;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

  private final AuthService authService;

  @PostMapping("/v1/auth/signup")
  public Response<SignAuthResponse> signup(
      @Valid @RequestBody SignUpAuthRequest request
  ) {
    SignAuthResponse response = authService.signup(request.getEmail(), request.getPassword(), request.getNickname());
    return Response.created(response);
  }

  @PostMapping("/v1/auth/signin")
  public Response<SignAuthResponse> signin(
      @Valid @RequestBody SignInAuthRequest request
  ) {
    SignAuthResponse response = authService.signin(request.getEmail(), request.getPassword());
    return Response.success(response);
  }

  @PostMapping("/v1/auth/email")
  public Response<EmailVerificationResponse> saveEmail(
      @Valid @RequestBody EmailRequest request
  ) {
    EmailVerificationResponse response = authService.saveEmail(request.getEmail());
    return Response.success(response);
  }

  @PostMapping("/v1/auth/verify")
  public Response<Void> verifyEmail(
      @Valid @RequestBody EmailVerificationRequest request
  ) {
    authService.checkEmail(request.getEmail(), request.getVerificationCode());
    return Response.success();
  }

}
