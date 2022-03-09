package com.liner.example.highlight.api.dto;

import com.liner.example.validator.AtLeastOneOfThem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneOfThem({ "pageId", "pageUrl" })
public class HighlightRetrieveDto {

    @NotNull
    private Long userId;

    private Long pageId;

    private String pageUrl;

}
