package com.preproject.server.global.error.exception;

import lombok.Getter;

@Getter
public enum ExceptionCode {
    MEMBER_EXISTS(409, "존재하는 이메일."),
    MEMBER_NOT_FOUND(404, "멤버를 찾을 수 없음."),
    INVALID_PERMISSION(403, "권한이 없음."),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류");

    private int status;
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
