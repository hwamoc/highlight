package com.liner.example.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND(404,"COMMON-ERR-404","PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500,"COMMON-ERR-500","INTER SERVER ERROR"),
    EMAIL_DUPLICATION(400,"MEMBER-ERR-400","EMAIL DUPLICATED"),
    NOT_FOUND_HIGHLIGHT(400, "HIGHLIGHT-ERR-400", "NOT FOUND HIGHLIGHT"),
    NOT_FOUND_PAGE(400, "PAGE-ERR-400", "NOT FOUND PAGE"),
    NOT_FOUND_THEME(400, "THEME-ERR-400", "NOT FOUND THEME"),
    NOT_FOUND_COLOR(400, "THEME-ERR-400", "NOT FOUND COLOR IN CURRENT THEME"),
    CANNOT_CHANGE_DEFAULT_THEME(400, "THEME-ERR-400", "CAN NOT CHANGE DEFAULT THEME")
    ;

    private int status;
    private String errorCode;
    private String message;

}