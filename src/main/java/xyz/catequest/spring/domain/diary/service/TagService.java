package xyz.catequest.spring.domain.diary.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.diary.entity.Tag;
import xyz.catequest.spring.domain.diary.repository.TagRepository;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.InvalidRequestException;
import xyz.catequest.spring.global.exception.NotFoundException;

@Service
@RequiredArgsConstructor
public class TagService {
  private final TagRepository tagRepository;

  @Transactional
  public Tag getByNameOrElseSave(String tagName, User user) {
    return tagRepository
        .findByNameAndCreator_Id(tagName, user.getId())
        .orElseGet(
            () -> {
              // 태그가 없으면 새로 생성
              Tag newTag = new Tag(tagName, user);
              return tagRepository.save(newTag);
            });
  }

  @Transactional
  public Tag saveTag(String tagName, User user) {
    if (tagRepository.existsByNameAndCreator_Id(tagName, user.getId())) {
      throw new InvalidRequestException(ErrorMessage.DUPLICATED_TAG);
    }
    return tagRepository.save(Tag.create(tagName, user));
  }

  @Transactional(readOnly = true)
  public Tag getByName(String tagName, Long userId) {
    return tagRepository
        .findByNameAndCreator_Id(tagName, userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_TAG));
  }

  @Transactional(readOnly = true)
  public List<Tag> getTagsByCreator(Long userId) {
    return tagRepository.findByCreator_Id(userId);
  }

  @Transactional(readOnly = true)
  public List<Tag> getExistingTags(List<String> tagNames, Long userId) {
    return tagRepository.findAllByNameInAndCreator_Id(tagNames, userId);
  }
}
