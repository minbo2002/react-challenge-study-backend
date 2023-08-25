package com.example.reactchallengestudybackend.domain.item.dto.request;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemCreateRequest {

    @NotBlank(message = "상품 이름은 필수로 입력해야합니다.")
    private String itemName;

    @Min(value = 0, message = "상품 가격은 0원 이상이어야합니다.")
    private Integer price;

    @Min(value = 0, message = "상품 재고개수는 0개 이상이어야합니다.")
    private Integer stockNumber;

    @NotBlank(message = "상품 설명은 필수로 입력해야합니다.")
    private String content;

    /* 상품 상태는 default로 ON_SALE(판매중)로 설정
    private ItemStatus itemStatus;
    */

    @Builder
    public ItemCreateRequest(String itemName, Integer price, Integer stockNumber, String content) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.content = content;
    }
}
