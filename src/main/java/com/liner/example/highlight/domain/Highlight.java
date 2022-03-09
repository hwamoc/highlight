package com.liner.example.highlight.domain;

import com.liner.example.common.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Highlight extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private String colorOrdinal;

    private String text;

    private Long pageId;
//    @ManyToOne
//    @JoinColumn(name = "page_id", insertable = false, updatable = false)
//    private Page page;

    public Highlight setUpdate(String colorOrdinal, String text) {
        this.colorOrdinal = colorOrdinal;
        this.text = text;
        return this;
    }

}
