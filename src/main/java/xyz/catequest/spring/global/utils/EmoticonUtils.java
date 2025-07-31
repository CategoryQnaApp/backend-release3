package xyz.catequest.spring.global.utils;

import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmoticonUtils {
  public static String fromEmoticon(List<String> emoticons) {
    StringBuilder emoticonStr = new StringBuilder();
    for (String emoticon : emoticons) {
      emoticonStr.append(emoticon).append(" ");
    }
    return emoticonStr.toString().trim();
  }

  public static List<String> fromString(String emoticons) {
    if (emoticons == null || emoticons.trim().isEmpty()) {
      return List.of();
    }
    return Arrays.stream(emoticons.trim().split("\\s+")).filter(s -> !s.isEmpty()).toList();
  }
}
