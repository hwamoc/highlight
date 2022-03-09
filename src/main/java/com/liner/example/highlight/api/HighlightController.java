package com.liner.example.highlight.api;

import com.liner.example.highlight.api.dto.HighlightDto;
import com.liner.example.highlight.api.dto.HighlightRequestDto;
import com.liner.example.highlight.api.dto.HighlightRetrieveDto;
import com.liner.example.highlight.api.dto.HighlightUpdateDto;
import com.liner.example.highlight.service.HighlightService;
import com.liner.example.page.domain.Page;
import com.liner.example.page.service.PageService;
import com.liner.example.response.ApiResponseMessage;
import com.liner.example.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/highlights")
public class HighlightController {

    @Autowired
    private HighlightService highlightService;

    @Autowired
    private PageService pageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HighlightDto add(@RequestBody @Valid HighlightRequestDto highLightRequest) {
        HighlightDto newHighlight = highlightService.add(highLightRequest);
        return newHighlight;
    }

    @PutMapping("/{id}")
    public HighlightDto updateById(@PathVariable("id") @NotNull Long id,
                                   @RequestBody @Valid HighlightUpdateDto highLightUpdate) {
        HighlightDto updatedHighlight = highlightService.updateById(id, highLightUpdate);
        return updatedHighlight;
    }

    @GetMapping()
    public List<HighlightDto> getListByPage(@RequestBody @Valid HighlightRetrieveDto highlightRetrieveDto) {
        List<HighlightDto> highlightDtoList = null;
        Long pageId;
        if (highlightRetrieveDto.getPageId() != null) {
            pageId = highlightRetrieveDto.getPageId();
        } else {
            Page page = pageService.getOneByPageUrl(highlightRetrieveDto.getPageUrl());
            pageId = page.getId();
        }
        highlightDtoList = highlightService.getListByPageId(pageId, highlightRetrieveDto.getUserId());
        return highlightDtoList;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMessage> delete(@PathVariable @NotNull Long id, @RequestParam @NotNull Long userId) {
        highlightService.delete(id);
        ApiResponseMessage message = new ApiResponseMessage(StatusEnum.OK.getStatusCode(), StatusEnum.OK.getCode(), "Successfully Deleted", null);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


}
