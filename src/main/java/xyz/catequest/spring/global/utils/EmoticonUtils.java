package xyz.catequest.spring.global.utils;

import java.util.Arrays;
import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
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
    return Arrays.stream(emoticons.split(" ")).toList();
  }
}
