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

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
    name = "talks",
    indexes = {@Index(name = "idx_neighbor_talk", columnList = "neighbor_id")})
public class Talk {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String content;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "neighbor_id", nullable = false)
  private Neighbor neighbor;

  protected void setNeighbor(Neighbor neighbor) {
    this.neighbor = neighbor;
  }

  protected void changeContent(String content) {
    // todo : 검증 로직 추가
    this.content = content;
  }
}
