package com.liner.example.highlight.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HighlightRequestDto {

    private Long pageId;

    @NotNull
    private Long userId;

    @NotNull
    private String colorHex;

    @NotNull
    private String pageUrl;

    @NotBlank
    @Size(max=6000, message="Text size must be less than 6000 characters")
    private String text;

}
