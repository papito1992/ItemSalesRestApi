package com.SalesAPI.SalesAPI.ItemData;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "SalesItemComment")
@Data
@Table(name = "item_comment")
public class SalesItemComment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_id_gen")
    @SequenceGenerator(name = "comment_id_gen", sequenceName = "comment_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String comment;
    public SalesItemComment(String comment) {
        this.comment = comment;
    }

    public SalesItemComment() {

    }

}
