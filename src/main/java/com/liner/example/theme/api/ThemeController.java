package com.liner.example.theme.api;

import com.liner.example.response.ResponseMessage;
import com.liner.example.response.StatusEnum;
import com.liner.example.theme.api.dto.ThemeDto;
import com.liner.example.theme.api.dto.ThemeRequestDto;
import com.liner.example.theme.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/themes")
public class ThemeController {

    @Autowired
    private ThemeService themeService;

    @PostMapping
    public ResponseEntity<ResponseMessage> add(@RequestBody @Valid ThemeRequestDto themeRequestDto) {
        ThemeDto newTheme = themeService.add(themeRequestDto);
        ResponseMessage message = new ResponseMessage(StatusEnum.CREATED.getStatusCode(), StatusEnum.CREATED.getCode(), "Successfully Created", newTheme);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateById(@PathVariable("id") @NotNull Long id,
                                                      @RequestBody @Valid ThemeRequestDto themeRequestDto) {
        ThemeDto themeDto = themeService.updateById(id, themeRequestDto);
        ResponseMessage message = new ResponseMessage(StatusEnum.OK.getStatusCode(), StatusEnum.OK.getCode(), "Successfully Updated", themeDto);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
