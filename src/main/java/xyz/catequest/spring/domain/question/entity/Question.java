package xyz.catequest.spring.domain.question.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "questions")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String question;

    @Column
    private String category;

    @Column
    private Long category_in_id;

    public Question(String question, String category, Long category_in_id)
    {
        this.question = question;
        this.category = category;
        this.category_in_id = category_in_id;
    }

}
