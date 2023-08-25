package com.example.reactchallengestudybackend.domain.item.dto.request;

import com.example.reactchallengestudybackend.domain.item.entity.ItemStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemUpdateRequest {

    private String itemName;
    private Integer price;
    private Integer stockNumber;
    private String content;
    private ItemStatus itemStatus;

    @Builder
    public ItemUpdateRequest(String itemName, Integer price, Integer stockNumber, String content, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.content = content;
        this.itemStatus = itemStatus;
    }
}
