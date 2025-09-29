package xyz.catequest.spring.domain.neighbor.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import xyz.catequest.spring.domain.neighbor.dto.response.NeighborResponse;
import xyz.catequest.spring.domain.neighbor.entity.Neighbor;
import xyz.catequest.spring.domain.neighbor.repository.NeighborRepository;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NeighborService {
    private final NeighborRepository neighborRepository;

    public void createNeighbor(String name, String neighborUrl, String description) {
        Neighbor newNeighbor = Neighbor.create(neighborUrl, name, description);
        neighborRepository.save(newNeighbor);
    }

    public NeighborResponse getNeighbor(Long neighborId) {
        Neighbor targetNeighbor = neighborRepository.findById(neighborId).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
        return NeighborResponse.from(targetNeighbor);
    }

    public List<NeighborResponse> getNeighbors() {
        return neighborRepository.findAll().stream().map(NeighborResponse::from).collect(Collectors.toList());
    }

    public void updateNeighborName(Long neighborId, String newName) {
        Neighbor targetNeighbor = neighborRepository.findById(neighborId).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
        targetNeighbor.updateName(newName);
    }

    public void updateNeighborUrl(Long neighborId, String newUrl) {
        Neighbor targetNeighbor = neighborRepository.findById(neighborId).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
        targetNeighbor.updateUrl(newUrl);
    }

    public void updateNeighborDescription(Long neighborId, String newDescription) {
        Neighbor targetNeighbor = neighborRepository.findById(neighborId).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
        targetNeighbor.updateDescription(newDescription);
    }
}
