package xyz.catequest.spring.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.users.dto.response.GetUserResponse;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.repository.UserRepository;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public GetUserResponse myInfo(Long userId) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
    return GetUserResponse.from(user);
  }

  @Transactional
  public void updateProfileImage(Long userId, byte[] image) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));

    // todo : s3 추가
    user.updateProfileImage("s3 주소 링크");
  }

  @Transactional
  public void updateNickname(Long userId, String nickname) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
    user.updateNickname(nickname);
  }

  @Transactional
  public void updatePassword(Long userId, String oldPassword, String newPassword) {
    User user =
        userRepository
            .findById(userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.USER_NOT_FOUND));
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
      throw new InvalidRequestException(ErrorMessage.WRONG_PASSWORD);
    }
    user.updatePassword(passwordEncoder.encode(newPassword));
  }

  @Transactional
  public void updateBookId(Long userId, Long npcId) {
    User user =
        userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    user.updateBooksId(npcId);
  }

    @Transactional
    public void deleteUser(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        user.softDelete();
    }
}
