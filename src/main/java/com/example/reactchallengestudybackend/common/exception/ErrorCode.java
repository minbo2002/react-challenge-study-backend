package com.example.reactchallengestudybackend.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // item
    NOT_FOUND_ITEM(HttpStatus.NOT_FOUND, "해당 상품을 찾을 수 없습니다.");

    private final HttpStatus httpStatus;
    private final String errorMessage;
}
