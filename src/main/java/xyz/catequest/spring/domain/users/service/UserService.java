package xyz.catequest.spring.domain.users.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.users.dto.response.GetUserResponse;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.repository.UserRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    // 내 정보 조회
    public GetUserResponse myInfo(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        return GetUserResponse.from(user);
    }

    public void updateProfileImage(Long userId, byte[] image) {

        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        // todo : s3 추가
//        user.setProfileImage("s3 주소 링크");
    }

    public void updateNickname(Long userId, String nickname) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        user.updateNickname(nickname);
    }

    public void updatePassword(Long userId, String password) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        // todo : 비밀번호 체크 및 변경될 비밀번호 암호화 후 저장
    }

    public void updateBookId(Long userId, Long npcId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));

        user.updateBooksId(npcId);
    }

    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found"));
        // todo : soft delete
        userRepository.delete(user);
    }
}
