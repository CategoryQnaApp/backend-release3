package xyz.catequest.spring.domain.neighbor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Entity
@Setter(AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "emoticons",
    indexes = {@Index(name = "idx_neighbor_emoticon", columnList = "neighbor_id")})
public class Emoticon {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(nullable = false, name = "emoticon_url")
  private String emoticonUrl;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "neighbor_id", nullable = false)
  private Neighbor neighbor;

  private Emoticon(String name, String emoticonUrl) {
    this.name = name;
    this.emoticonUrl = emoticonUrl;
  }

  public static Emoticon create(String name, String emoticonUrl) {
    return new Emoticon(name, emoticonUrl);
  }

  protected void changeName(String newName) {
    this.name = newName;
  }

  protected void changeEmoticonUrl(String newEmoticonUrl) {
    this.emoticonUrl = newEmoticonUrl;
  }

  protected void setNeighbor(Neighbor neighbor) {
    this.neighbor = neighbor;
  }
}
