package xyz.catequest.spring.domain.neighbor.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import xyz.catequest.spring.domain.neighbor.dto.request.CreateNeighborRequest;
import xyz.catequest.spring.domain.neighbor.dto.request.NeighborEmoticonRequest;
import xyz.catequest.spring.domain.neighbor.dto.request.NeighborTalkRequest;
import xyz.catequest.spring.domain.neighbor.dto.response.NeighborResponse;
import xyz.catequest.spring.domain.neighbor.entity.Neighbor;
import xyz.catequest.spring.domain.neighbor.repository.NeighborRepository;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class NeighborService {
  private final NeighborRepository neighborRepository;

  @Transactional
  public void createNeighbor(String name, String neighborUrl, String description) {
    Neighbor newNeighbor = Neighbor.create(neighborUrl, name, description);
    neighborRepository.save(newNeighbor);
  }

  @Transactional
  public void createNeighbor(CreateNeighborRequest request) {
    Neighbor newNeighbor =
        Neighbor.create(
            request.getName(),
            "tempURL",
            request.getDescription(),
            request.getTalks().stream().map(NeighborTalkRequest::getContent).toList(),
            request.getEmoticons().stream().map(NeighborEmoticonRequest::getName).toList(),
            request.getEmoticons().stream()
                .map(NeighborEmoticonRequest::getEmoticonImage)
                .map(Object::toString)
                .toList());
    neighborRepository.save(newNeighbor);
  }

  @Transactional
  public void createNeighbor(
      String name,
      MultipartFile neighborImage,
      String description,
      List<String> neighborTalks,
      List<String> emoticonNames,
      List<MultipartFile> emoticonImages) {
    Neighbor newNeighbor =
        Neighbor.create(
            name,
            "tempURL",
            description,
            neighborTalks,
            emoticonNames,
            emoticonImages.stream().map(Objects::toString).toList());
    neighborRepository.save(newNeighbor);
  }

  @Transactional(readOnly = true)
  public NeighborResponse getNeighbor(Long neighborId) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    return NeighborResponse.from(targetNeighbor);
  }

  @Transactional(readOnly = true)
  public List<NeighborResponse> getNeighbors() {
    return neighborRepository.findAll().stream()
        .map(NeighborResponse::from)
        .collect(Collectors.toList());
  }

  @Transactional
  public void updateNeighborName(Long neighborId, String newName) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.updateName(newName);
  }

  @Transactional
  public void updateNeighborUrl(Long neighborId, String newUrl) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.updateUrl(newUrl);
  }

  @Transactional
  public void updateNeighborDescription(Long neighborId, String newDescription) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.updateDescription(newDescription);
  }

  @Transactional
  public void updateNeighborTalk(Long neighborId, Long talkId, String newTalk) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.changeTalk(talkId, newTalk);
  }

  @Transactional
  public void updateNeighborEmoticon(
      Long neighborId, Long emoticonId, String newEmoticonName, MultipartFile newEmoticon) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.changeEmoticon(
        emoticonId, newEmoticonName, newEmoticon.toString() /*todo : 이미지 링크로 변경하기*/);
  }

  @Transactional
  public void updateNeighborEmoticonName(Long neighborId, Long emoticonId, String newEmoticonName) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.changeEmoticonName(emoticonId, newEmoticonName);
  }

  @Transactional
  public void updateNeighborEmoticonImage(
      Long neighborId, Long emoticonId, MultipartFile newEmoticon) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.changeEmoticonUrl(emoticonId, newEmoticon.toString() /*todo : 이미지 링크로 변경하기*/);
  }

  @Transactional
  public void deleteNeighborTalk(Long neighborId, Long talkId) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.removeTalk(talkId);
  }

  @Transactional
  public void deleteNeighborEmoticon(Long neighborId, Long emoticonId) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    targetNeighbor.removeEmoticon(emoticonId);
  }

  @Transactional
  public void removeNeighbor(Long neighborId) {
    Neighbor targetNeighbor =
        neighborRepository
            .findById(neighborId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_NEIGHBOR));
    neighborRepository.delete(targetNeighbor);
  }
}
