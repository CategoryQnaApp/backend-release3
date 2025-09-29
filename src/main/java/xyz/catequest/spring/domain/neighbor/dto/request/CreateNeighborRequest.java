package xyz.catequest.spring.domain.neighbor.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateNeighborRequest {
    private final String name;
    private final String neighborUrl;
    private final String description;
    // todo : Talk, Emoticon dto내용추가
}
