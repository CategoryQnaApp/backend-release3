package xyz.catequest.spring.domain.neighbor.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import xyz.catequest.spring.domain.neighbor.entity.Emoticon;
import xyz.catequest.spring.domain.neighbor.entity.Neighbor;
import xyz.catequest.spring.domain.neighbor.entity.Talk;

import java.util.List;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class NeighborResponse {
    private final Long id;
    private final String name;
    private final String neighborUrl;
    private final List<Talk> talks;
    private final List<Emoticon> emoticons;

    // 정적 팩토리 메소드
    public static NeighborResponse from(Neighbor neighbor) {
        return new NeighborResponse(neighbor.getId(), neighbor.getName(), neighbor.getNeighborUrl(), neighbor.getTalks(), neighbor.getEmoticons());
    }
}
