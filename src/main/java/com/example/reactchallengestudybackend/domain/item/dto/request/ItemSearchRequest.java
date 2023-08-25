package com.example.reactchallengestudybackend.domain.item.dto.request;

import com.example.reactchallengestudybackend.domain.item.entity.ItemStatus;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ItemSearchRequest {

    private String itemName;
    private Integer price;
    private String content;
    private ItemStatus itemStatus;

    @Builder
    public ItemSearchRequest(String itemName, Integer price, String content, ItemStatus itemStatus) {
        this.itemName = itemName;
        this.price = price;
        this.content = content;
        this.itemStatus = itemStatus;
    }
}
