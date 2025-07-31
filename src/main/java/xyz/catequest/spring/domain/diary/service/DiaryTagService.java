package xyz.catequest.spring.domain.diary.service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.diary.dto.request.DiaryRequest;
import xyz.catequest.spring.domain.diary.dto.response.DiaryResponse;
import xyz.catequest.spring.domain.diary.entity.Diary;
import xyz.catequest.spring.domain.diary.entity.DiaryTag;
import xyz.catequest.spring.domain.diary.entity.Tag;
import xyz.catequest.spring.domain.diary.repository.DiaryTagRepository;
import xyz.catequest.spring.domain.users.entity.User;
import xyz.catequest.spring.domain.users.service.UserService;

@Service
@RequiredArgsConstructor
public class DiaryTagService {
  private final DiaryTagRepository diaryTagRepository;
  private final DiaryService diaryService;
  private final TagService tagService;
  private final UserService userService;

  @Transactional
  public void addTagToDiary(Long diaryId, List<String> tagNames, Long userId) {
    User user = userService.getUserEntity(userId);
    Diary diary = diaryService.getDiaryEntityByUserId(diaryId, userId);

    saveDiaryTag(tagNames, diary, user);
  }

  @Transactional
  public DiaryResponse saveDiary(DiaryRequest request, Long userId) {
    User user = userService.getUserEntity(userId);
    Diary savedDiary =
        diaryService.saveDiary(
            user, request.getContent(), request.getEmoticons(), request.getSavedTime());

    saveDiaryTag(request.getTagList(), savedDiary, user);
    return DiaryResponse.from(savedDiary, request.getTagList());
  }

  @Transactional
  public DiaryResponse updateDiary(Long diaryId, DiaryRequest request, Long userId) {
    User user = userService.getUserEntity(userId);
    Diary updateDiary = diaryService.getDiaryEntityByUserId(diaryId, userId);

    // Note1: 일기 업데이트 시 Diary 와 Tag의 연결을 끊음
    diaryTagRepository.deleteByDiary(updateDiary);
    // Note2: 연결을 끊은 후 새롭게 다시 연결
    saveDiaryTag(request.getTagList(), updateDiary, user);
    return DiaryResponse.from(updateDiary);
  }

  @Transactional
  public void updateDiaryTag(Long diaryId, List<String> tagNames, Long userId) {
    User user = userService.getUserEntity(userId);
    Diary updateDiary = diaryService.getDiaryEntityByUserId(diaryId, userId);

    diaryTagRepository.deleteByDiary(updateDiary);
    saveDiaryTag(tagNames, updateDiary, user);
  }

  @Transactional
  public void removeTagFromDiary(Long diaryId, List<String> tagNames, Long userId) {
    User user = userService.getUserEntity(userId);
    Diary diary = diaryService.getDiaryEntityByUserId(diaryId, userId);
    for (String tagName : tagNames) {
      Tag tag = tagService.getByName(tagName, user.getId());

      DiaryTag diaryTag =
          diaryTagRepository
              .findByDiaryAndTag(diary, tag)
              .orElseThrow(() -> new RuntimeException("해당 태그가 일기에 없음"));

      diaryTagRepository.delete(diaryTag);
    }
  }

  private void saveDiaryTag(List<String> tagNames, Diary diary, User user) {
    List<Tag> existingTags = tagService.getExistingTags(tagNames, user.getId());
    Map<String, Tag> tagMap =
        existingTags.stream().collect(Collectors.toMap(Tag::getName, Function.identity()));

    for (String tagName : tagNames) {
      Tag tag = tagMap.get(tagName);
      if (tag == null) {
        tag = tagService.saveTag(tagName, user);
      }
      DiaryTag diaryTag = DiaryTag.createDiaryTag(diary, tag);
      diaryTagRepository.save(diaryTag);
    }
  }
}
