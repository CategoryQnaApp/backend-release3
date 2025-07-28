package xyz.catequest.spring.domain.auth.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.auth.dto.response.EmailAuthResponse;
import xyz.catequest.spring.domain.auth.dto.response.SignAuthResponse;
import xyz.catequest.spring.domain.auth.entity.EmailAuth;
import xyz.catequest.spring.domain.auth.entity.RefreshToken;
import xyz.catequest.spring.domain.auth.enums.EmailStatus;
import xyz.catequest.spring.domain.auth.repository.EmailAuthRepository;
import xyz.catequest.spring.domain.auth.repository.RefreshTokenRepository;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.repository.UserRepository;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;
import xyz.catequest.spring.global.exception.NotFoundException;
import xyz.catequest.spring.global.utils.EmailChecker;
import xyz.catequest.spring.global.utils.JwtProvider;
import xyz.catequest.spring.global.utils.SecureRandomCodeGenerator;

@Service
@RequiredArgsConstructor
public class AuthService {

  private final UserRepository userRepository;
  private final EmailAuthRepository emailAuthRepository;
  private final RefreshTokenRepository refreshTokenRepository;
  private final JwtProvider jwtProvider;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public SignAuthResponse signup(String email, String password, String nickname) {
    if (EmailChecker.isValid(email)) {
      throw new InvalidRequestException(ErrorMessage.INVALID_EMAIL);
    }
    if (userRepository.existsByEmail(email)) {
      throw new InvalidRequestException(ErrorMessage.DUPLICATED_EMAIL);
    }
    isVerifiedEmail(email);

    String encodedPassword = passwordEncoder.encode(password);
    User savedUser = userRepository.save(User.of(email, encodedPassword, nickname));

    String accessToken = jwtProvider.createAccessToken(savedUser);
    String refreshToken = jwtProvider.createRefreshToken(savedUser);
    refreshTokenRepository.save(RefreshToken.of(savedUser, refreshToken));

    return SignAuthResponse.of(accessToken, refreshToken);
  }

  private void isVerifiedEmail(String email) {
    EmailAuth verifiedEmail =
        emailAuthRepository
            .findByEmail(email)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMAIL));
    if (!EmailStatus.isVerified(verifiedEmail.getEmailStatus())) {
      throw new InvalidRequestException(ErrorMessage.UNVERIFIED_EMAIL);
    }
    emailAuthRepository.delete(verifiedEmail);
  }

  @Transactional
  public SignAuthResponse signin(String email, String password) {
    User findUser =
        userRepository
            .findByEmail(email)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
    if (!passwordEncoder.matches(password, findUser.getPassword())) {
      throw new InvalidRequestException(ErrorMessage.WRONG_PASSWORD);
    }

    String accessToken = jwtProvider.createAccessToken(findUser);
    String refreshToken = jwtProvider.createRefreshToken(findUser);
    if (refreshTokenRepository.existsByUserId(findUser.getId())) {
      refreshTokenRepository.deleteByUserId(findUser.getId());
    }
    refreshTokenRepository.save(RefreshToken.of(findUser, refreshToken));

    return SignAuthResponse.of(accessToken, refreshToken);
  }

  @Transactional(readOnly = true)
  public String refreshAccessToken(String refreshToken) {
    if (!refreshTokenRepository.existsByRefreshToken(refreshToken)) {
      throw new InvalidRequestException(ErrorMessage.INVALID_REFRESH_TOKEN);
    }

    long userId;
    try {
      Claims claims = jwtProvider.extractClaims(refreshToken);
      userId = Long.parseLong(claims.getSubject());
    } catch (ExpiredJwtException e) {
      throw new InvalidRequestException(ErrorMessage.EXPIRED_REFRESH_TOKEN);
    } catch (JwtException e) {
      throw new InvalidRequestException(ErrorMessage.INVALID_REFRESH_TOKEN);
    }

    User findUser =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));

    return jwtProvider.createAccessToken(findUser);
  }

  @Transactional
  public EmailAuthResponse saveEmail(String email) {
    if (emailAuthRepository.existsByEmail(email)) {
      throw new InvalidRequestException(ErrorMessage.DUPLICATED_EMAIL);
    }
    EmailAuth verification = EmailAuth.of(email, SecureRandomCodeGenerator.generateRandomCode());
    emailAuthRepository.save(verification);

    // todo: aws sqs && ses 적용
    return EmailAuthResponse.of(verification.getVerificationCode());
  }

  @Transactional
  public void verifyEmail(String email, String number) {
    EmailAuth findEmail =
        emailAuthRepository
            .findByEmail(email)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMAIL));

    if (!findEmail.getVerificationCode().equals(number)) {
      throw new InvalidRequestException(ErrorMessage.INCORRECT_AUTH_NUMBER);
    }

    findEmail.markAsVerified();
  }
}
