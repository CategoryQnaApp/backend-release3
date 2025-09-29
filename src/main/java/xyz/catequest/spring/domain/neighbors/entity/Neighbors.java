package xyz.catequest.spring.domain.neighbors.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.ObjectUtils;
import xyz.catequest.spring.global.entity.BaseEntity;
import xyz.catequest.spring.global.enums.ErrorMessage;
import xyz.catequest.spring.global.exception.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "neighbors")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Neighbors extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(name = "neighbor_url")
  private String neighborUrl;

  @Column(nullable = false, length = 20)
  private String description;

  @OneToMany(mappedBy = "neighbors", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Talk> talks = new ArrayList<>();

  @OneToMany(mappedBy = "neighbors", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Emoticon> emoticons = new ArrayList<>();

  // 생성자
  private Neighbors(String name, String  neighborUrl, String description) {
    this.name = name;
    this.neighborUrl = neighborUrl;
    this.description = description;
  }

  // 정적 팩토리 메서드
  public static Neighbors create(String name, String neighborUrl, String description) {
    return new Neighbors(name, neighborUrl, description);
  }

  public static Neighbors create(String name, String neighborUrl, String description, List<Talk> talks, List<Emoticon> emoticons) {
    Neighbors neighbors = new Neighbors(name, neighborUrl, description);
    for( Talk talk : talks) {
      neighbors.addTalk(talk);
    }
    for( Emoticon emoticon : emoticons) {
      neighbors.addEmoticon(emoticon);
    }
    return neighbors;
  }

  // 행위
  public void addTalk(Talk talk) {
    this.talks.add(talk);
    talk.setNeighbors(this);
  }

  public void addEmoticon(Emoticon emoticon) {
    this.emoticons.add(emoticon);
    emoticon.setNeighbors(this);
  }

  public void removeTalk(Long talkId) {
    Talk targetTalk = this.talks.stream().filter(t -> t.getId().equals(talkId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_TALKS));
    this.talks.remove(targetTalk);
  }

  public void changeTalk(Long talkId, String talkContent) {
    Talk targetTalk = this.talks.stream().filter(t -> t.getId().equals(talkId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_TALKS));
    targetTalk.changeContent(talkContent);
  }

  public void removeEmoticon(Long emoticonId) {
    Emoticon targetEmoticon = this.emoticons.stream().filter(e -> e.getId().equals(emoticonId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMOTICON));
    this.emoticons.remove(targetEmoticon);
  }

  public void changeEmoticonName(Long emoticonId, String newName) {
    Emoticon targetEmoticon = this.emoticons.stream().filter(e -> e.getId().equals(emoticonId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMOTICON));
    targetEmoticon.changeName(newName);
  }

  public void changeEmoticonUrl(Long emoticonId, String newUrl) {
    Emoticon targetEmoticon = this.emoticons.stream().filter(e -> e.getId().equals(emoticonId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMOTICON));
    targetEmoticon.changeEmoticonUrl(newUrl);
  }

  public void changeEmoticon(Long emoticonId, String newName, String newUrl) {
    Emoticon targetEmoticon = this.emoticons.stream().filter(e -> e.getId().equals(emoticonId)).findFirst().orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND_EMOTICON));
    targetEmoticon.changeName(newName);
    targetEmoticon.changeEmoticonUrl(newUrl);
  }

}


