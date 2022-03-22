package com.liner.example.user.api;

import com.liner.example.response.ResponseMessage;
import com.liner.example.response.StatusEnum;
import com.liner.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/{id}")
    public ResponseEntity<ResponseMessage> updateById(@PathVariable("id") @NotNull Long id,
                                                      @Valid @NotNull @RequestParam Long themeId) {
        userService.changeTheme(id, themeId);
        ResponseMessage message = new ResponseMessage(StatusEnum.OK.getStatusCode(), StatusEnum.OK.getCode(), "Successfully Updated", null);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
