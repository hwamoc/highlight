package com.liner.example.highlight.service;

import com.liner.example.exception.ErrorCode;
import com.liner.example.exception.NotFoundColorInCurrentTheme;
import com.liner.example.exception.NotFound;
import com.liner.example.highlight.api.dto.HighlightDto;
import com.liner.example.highlight.api.dto.HighlightRequestDto;
import com.liner.example.highlight.api.dto.HighlightUpdateDto;
import com.liner.example.highlight.domain.Highlight;
import com.liner.example.highlight.domain.repo.HighlightRepository;
import com.liner.example.page.domain.Page;
import com.liner.example.page.domain.repo.PageRepository;
import com.liner.example.page.service.PageService;
import com.liner.example.theme.domain.Theme;
import com.liner.example.user.domain.User;
import com.liner.example.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HighlightService {
    @Autowired
    private UserService userService;
    @Autowired
    private PageService pageService;
    @Autowired
    private HighlightRepository highlightRepository;
    @Autowired
    private PageRepository pageRepository;
    @Autowired
    private ModelMapper modelMapper;

    public HighlightDto add(HighlightRequestDto highlightRequestDto) {
        Long pageId = highlightRequestDto.getPageId();
        if (pageId == null) {
            Page newPage = pageService.add(highlightRequestDto.getUserId(), highlightRequestDto.getPageUrl());
            pageId = newPage.getId();
        } else {
            Page page = pageRepository.findById(pageId).orElseThrow(() -> new NotFound("Not Found Theme", ErrorCode.NOT_FOUND_PAGE));
            pageRepository.save(page);
        }

        User user = userService.getUser(highlightRequestDto.getUserId());
        Theme theme = user.getTheme();
        String colorOrdinal;
        boolean contains = theme.checkColor(theme, highlightRequestDto.getColorHex());
        if (contains) {
            colorOrdinal = theme.getColorOrdinal(theme, highlightRequestDto.getColorHex());
        } else {
            throw new NotFoundColorInCurrentTheme("Not Found Color In Current Theme", ErrorCode.NOT_FOUND_COLOR);
        }

        Highlight highlight = Highlight.builder()
                .pageId(pageId)
                .userId(highlightRequestDto.getUserId())
                .colorOrdinal(colorOrdinal)
                .text(highlightRequestDto.getText())
                .build();

        Highlight newHighlight = highlightRepository.save(highlight);
        HighlightDto highLightDto = modelMapper.map(newHighlight, HighlightDto.class);
        highLightDto.setColorHex(highlightRequestDto.getColorHex());
        return highLightDto;
    }

    public HighlightDto updateById(Long id, HighlightUpdateDto highlightUpdateDto) {
        Highlight highLight = highlightRepository.findById(id).orElseThrow(() -> new NotFound("Not Found Highlight", ErrorCode.NOT_FOUND_HIGHLIGHT));
        HighlightDto highlightDto = null;

        User user = userService.getUser(highlightUpdateDto.getUserId());
        Theme theme = user.getTheme();
        String text = highLight.getText();
        String colorOrdinal = highLight.getColorOrdinal();
        String colorHexOrigin = theme.getColorHex(theme, colorOrdinal);
        String colorHex = highlightUpdateDto.getColorHex();
        if (colorHex == null || (colorHex != null && colorHex.equals(colorHexOrigin))) {
            colorHex = colorHexOrigin;
        } else {
            boolean contains = theme.checkColor(theme, colorHex);
            if (contains) {
                colorOrdinal = theme.getColorOrdinal(theme, colorHex);
            } else {
                throw new NotFoundColorInCurrentTheme("Not Found Color In Current Theme", ErrorCode.NOT_FOUND_COLOR);
            }
        }
        if (highlightUpdateDto.getText() != null) {
            text = highlightUpdateDto.getText();
        }
        highLight.setUpdate(colorOrdinal, text);
        Highlight updatedHighlight = highlightRepository.save(highLight);
        highlightDto = modelMapper.map(updatedHighlight, HighlightDto.class);
        highlightDto.setColorHex(colorHex);
        return highlightDto;
    }

    public List<HighlightDto> getListByPageId(Long pageId, Long userId) {
        User user = userService.getUser(userId);
        Theme theme = user.getTheme();

        List<Highlight> highlightList = highlightRepository.selectJPQLByPageId(pageId);
        List<HighlightDto> highlightDtoList =  highlightList
                .stream()
                .map(highlight -> modelMapper.map(highlight, HighlightDto.class))
                .collect(Collectors.toList());

        for (HighlightDto dto : highlightDtoList) {
            dto.setColorHex(theme.getColorHex(theme, dto.getColorOrdinal()));
        }
        return highlightDtoList;
    }

    public void delete(Long id) {
        highlightRepository.deleteById(id);
    }
}
