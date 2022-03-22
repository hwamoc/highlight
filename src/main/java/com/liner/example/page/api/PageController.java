package com.liner.example.page.api;

import com.liner.example.highlight.api.dto.HighlightDto;
import com.liner.example.highlight.service.HighlightService;
import com.liner.example.page.domain.Page;
import com.liner.example.page.service.PageService;
import com.liner.example.response.ResponseMessage;
import com.liner.example.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseMessage> getListByPage(@PathVariable("id") @NotNull Long id, @RequestParam @NotNull Long userId) {
        List<HighlightDto> highlightList = null;
        highlightList = highlightService.getListByPageId(id, userId);
        ResponseMessage message = new ResponseMessage(StatusEnum.OK.getStatusCode(), StatusEnum.OK.getCode(), "Successfully Retrieved", highlightList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<ResponseMessage> getListByUserId(@PathVariable Long userId) {
        List<Page> pageList = pageService.getListByUserId(userId);
        ResponseMessage message = new ResponseMessage(StatusEnum.OK.getStatusCode(), StatusEnum.OK.getCode(), "Successfully Retrieved", pageList);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
