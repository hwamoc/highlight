package com.liner.example.theme.service;

import com.liner.example.exception.CannotChangeDefaultTheme;
import com.liner.example.exception.ErrorCode;
import com.liner.example.exception.NotFound;
import com.liner.example.theme.api.dto.ThemeDto;
import com.liner.example.theme.api.dto.ThemeRequestDto;
import com.liner.example.theme.domain.Theme;
import com.liner.example.theme.domain.repo.ThemeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThemeService {

    @Autowired
    private ThemeRepository themeRepository;
    @Autowired
    private ModelMapper modelMapper;

    public ThemeDto add(ThemeRequestDto themeRequestDto) {
        Theme theme = Theme.builder()
                .type("Custom")
                .createdBy(themeRequestDto.getUserId())
                .first(themeRequestDto.getFirst())
                .second(themeRequestDto.getSecond())
                .third(themeRequestDto.getThird())
                .fourth(themeRequestDto.getFourth())
                .fifth(themeRequestDto.getFifth())
                .sixth(themeRequestDto.getSixth())
                .build();
        Theme newTheme = themeRepository.save(theme);
        ThemeDto themeDto = modelMapper.map(newTheme, ThemeDto.class);
        return themeDto;
    }

    public ThemeDto updateById(Long id, ThemeRequestDto themeRequestDto) {
        Theme theme = themeRepository.findById(id).orElseThrow(() -> new NotFound("Not Found Theme", ErrorCode.NOT_FOUND_THEME));
        ThemeDto themeDto = null;
        if (theme.getType() == "Default") {
            throw new CannotChangeDefaultTheme("Can Not Change Default Theme", ErrorCode.CANNOT_CHANGE_DEFAULT_THEME);
        }
        theme.setUpdate(themeRequestDto);
        Theme updatedTheme = themeRepository.save(theme);
        themeDto = modelMapper.map(updatedTheme, ThemeDto.class);
        return themeDto;
    }
}
