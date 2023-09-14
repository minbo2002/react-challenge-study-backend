package com.example.reactchallengestudybackend.domain.item.dto.request;

import com.example.reactchallengestudybackend.domain.item.entity.ItemStatus;
import lombok.*;

@Getter
@Setter
@ToString
public class ItemSearchRequest {

    private String itemName;
    private Integer price;
    private String content;
    private ItemStatus itemStatus;
}
