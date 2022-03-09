package com.liner.example.page.api;

import com.liner.example.highlight.api.dto.HighlightDto;
import com.liner.example.highlight.service.HighlightService;
import com.liner.example.page.domain.Page;
import com.liner.example.page.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping(value = "/pages")
public class PageController {

    @Autowired
    private PageService pageService;

    @Autowired
    private HighlightService highlightService;

    @GetMapping("/{id}")
    public List<HighlightDto> getListByPage(@PathVariable("id") @NotNull Long id, @RequestParam @NotNull Long userId) {
        List<HighlightDto> highlightList = null;
        highlightList = highlightService.getListByPageId(id, userId);
        return highlightList;
    }

    @GetMapping("/users/{userId}")
    public List<Page> getListByUserId(@PathVariable Long userId) {
        List<Page> pageList = pageService.getListByUserId(userId);
        return pageList;
    }
}
