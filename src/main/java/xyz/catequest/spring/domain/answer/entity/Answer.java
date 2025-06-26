package xyz.catequest.spring.domain.answer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import xyz.catequest.spring.global.entity.BaseEntity;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "ANSWERS")
public class Answer extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "contents")
	private String contents;

	// @Column(name = "post_page")
	// private String postPage;
	//
	// @Column(name = "item_count")
	// private Long characterLimitItemCount;

	public Answer(String answer) {
		this.contents = answer;
	}
}
