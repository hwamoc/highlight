package com.liner.example.highlight.api.dto;

import com.liner.example.validator.AtLeastOneOfThem;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AtLeastOneOfThem({ "colorHex", "text" })
public class HighlightUpdateDto {

    @NotNull
    private Long userId;

    private String colorHex;

    @Size(max=6000, message="Text size must be less than 6000 characters")
    private String text;

}
