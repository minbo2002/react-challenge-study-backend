package com.example.reactchallengestudybackend.domain.item.entity;

public enum ItemStatus {

    ON_SALE("판매중"),
    SOLD_OUT("판매완료"),
    HIDDEN("숨김");

    private String title;

    ItemStatus(String title) {
        this.title = title;
    }
}
