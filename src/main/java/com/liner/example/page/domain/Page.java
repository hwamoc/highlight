package com.liner.example.page.domain;

import com.liner.example.common.BaseTimeEntity;
import com.liner.example.highlight.domain.Highlight;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Page extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private Long userId;

    private String pageUrl;

    @OneToMany
    @JoinColumn(name="pageId")
    @OrderBy ("modifiedDate desc, createdDate desc")
    private List<Highlight> highlight = new ArrayList<>();

}
