package com.shop.magazine.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    //Common
    INVALID_INPUT_VALUE(400, "C001", " Invalid Input Value"),
    METHOD_NOT_ALLOWED(405, "C002", " 사용할 수 없는 경로입니다"),
    NOT_FOUND(404, "C003", "요청하신 페이지를 찾을 수 없습니다"),

    USER_NOT_FOUND(404, "C004", "유저가 찾을 수 없습니다");


    private final int status;
    private final String code;
    private final String message;
}
