package com.example.reactchallengestudybackend.domain.item.dto.response;

import com.example.reactchallengestudybackend.domain.item.entity.Item;
import com.example.reactchallengestudybackend.domain.item.entity.ItemStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@ToString
public class ItemResponse {

    private final Long id;
    private final String itemName;
    private final Integer price;
    private final Integer stockNumber;
    private final String content;
    private final ItemStatus itemStatus;

    @Builder
    public ItemResponse(Long id, String itemName, Integer price, Integer stockNumber, String content, ItemStatus itemStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.content = content;
        this.itemStatus = itemStatus;
    }

    static public ItemResponse mapToDto(Item item) {
        return ItemResponse.builder()
                .id(item.getId())
                .itemName(item.getItemName())
                .price(item.getPrice())
                .stockNumber(item.getStockNumber())
                .content(item.getContent())
                .itemStatus(item.getItemStatus())
                .build();
    }
}
