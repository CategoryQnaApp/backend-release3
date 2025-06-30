package xyz.catequest.spring.domain.users.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.users.entity.User;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class GetUserResponse {
    private final String email;

    private final String nickname;

    private final String profileImage;


    public static GetUserResponse from(User user) {
        return GetUserResponse.of(user.getEmail(), user.getNickname(), user.getProfileImage());
    }
}
