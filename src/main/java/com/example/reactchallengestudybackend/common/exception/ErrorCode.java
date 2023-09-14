package com.example.reactchallengestudybackend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // item
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다."),

    // token
    INVALID_JWT_SIGNATURE(HttpStatus.BAD_REQUEST, "잘못된 JWT 서명입니다."),
    INVALID_JWT_TOKEN(HttpStatus.BAD_REQUEST, "유효하지 않은 토큰입니다."),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "만료된 토큰입니다."),
    UNSUPPORTED_TOKEN(HttpStatus.BAD_REQUEST, "지원되지 않는 토큰입니다."),
    JWT_CLAIMS_EMPTY(HttpStatus.BAD_REQUEST, "JWT 클레임이 비어있습니다."),

    // user
    USERNAME_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 회원입니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND,"해당되는 유저를 찾을 수 없습니다."),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당되는 권한을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
