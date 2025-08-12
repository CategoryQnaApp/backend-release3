package xyz.catequest.spring.domain.diary.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.catequest.spring.domain.diary.dto.response.DiaryResponse;
import xyz.catequest.spring.domain.diary.entity.Diary;
import xyz.catequest.spring.domain.diary.projection.DiaryFlatProjection;
import xyz.catequest.spring.domain.diary.repository.DiaryRepository;
import xyz.catequest.spring.domain.todo.service.TodoService;
import xyz.catequest.spring.domain.user.entity.User;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;
import xyz.catequest.spring.global.utils.EmoticonUtils;

@Service
@RequiredArgsConstructor
public class DiaryService {
  private final DiaryRepository diaryRepository;
  private final TodoService todoService;

  // todo: save, update 에 이미지 수정 로직 넣기
  @Transactional
  public Diary saveDiary(
      User user, String content, List<String> emoticonList, LocalDateTime savedTime) {
    String emoticons = EmoticonUtils.fromEmoticon(emoticonList);
    Diary diary = Diary.of(user, content, emoticons, savedTime);
    todoService.diaryDone(user.getId());
    return diaryRepository.save(diary);
  }

  @Transactional
  public Diary updateDiary(
      Long diaryId,
      Long userId,
      String content,
      List<String> emoticonList,
      LocalDateTime savedTime) {
    Diary diary =
        diaryRepository
            .findByIdAndUser_id(diaryId, userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DIARY));
    diary.updateContent(content);
    diary.updateEmoticons(EmoticonUtils.fromEmoticon(emoticonList));
    diary.updateSavedTime(savedTime);
    return diaryRepository.save(diary);
  }

  @Transactional(readOnly = true)
  public Diary getDiaryEntity(Long id) {
    return diaryRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DIARY));
  }

  @Transactional(readOnly = true)
  public Diary getDiaryEntityByUserId(Long diaryId, Long userId) {
    return diaryRepository
        .findByIdAndUser_id(diaryId, userId)
        .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DIARY));
  }

  @Transactional(readOnly = true)
  public DiaryResponse getDiary(Long diaryId, Long userId) {
    Diary diary =
        diaryRepository
            .findByIdWithTags(diaryId, userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DIARY));
    return DiaryResponse.from(diary);
  }

  @Transactional(readOnly = true)
  public List<DiaryResponse> getDiaries(Long userId) {
    List<DiaryFlatProjection> flats = diaryRepository.findDiariesWithTagsByUserId(userId);
    Map<Long, DiaryResponse> diaryMap = new LinkedHashMap<>();
    buildDiaryResponseMap(flats, diaryMap);

    return new ArrayList<>(diaryMap.values());
  }

  @Transactional(readOnly = true)
  public List<DiaryResponse> getDiariesByTag(String tag, Long userId) {
    List<DiaryFlatProjection> flats =
        diaryRepository.findDiariesWithTagsByTagNameAndUserId(tag, userId);
    Map<Long, DiaryResponse> diaryMap = new LinkedHashMap<>();
    buildDiaryResponseMap(flats, diaryMap);

    return new ArrayList<>(diaryMap.values());
  }

  @Transactional
  public void deleteDiary(Long diaryId, Long userId) {
    Diary diary =
        diaryRepository
            .findByIdAndUser_id(diaryId, userId)
            .orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_DIARY));
    diaryRepository.delete(diary);
  }

  private void buildDiaryResponseMap(
      List<DiaryFlatProjection> flats, Map<Long, DiaryResponse> diaryMap) {
    for (DiaryFlatProjection flat : flats) {
      DiaryResponse diary =
          diaryMap.computeIfAbsent(
              flat.getDiaryId(),
              id ->
                  DiaryResponse.of(
                      id,
                      flat.getContent(),
                      flat.getImageUrl(),
                      EmoticonUtils.fromString(flat.getEmoticons()), // 내부에서 파싱됨
                      new ArrayList<>(), // 태그 리스트는 아래에서 채움
                      flat.getSavedAt()));

      // 태그 추가 (중복 방지)
      if (flat.getTagName() != null && !diary.getTags().contains(flat.getTagName())) {
        diary.getTags().add(flat.getTagName());
      }
    }
  }
}
