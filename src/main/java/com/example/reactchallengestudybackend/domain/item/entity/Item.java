package com.example.reactchallengestudybackend.domain.item.entity;

import com.example.reactchallengestudybackend.domain.base.BaseTimeEntity;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemCreateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemUpdateRequest;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@ToString
@Table(name = "items")
public class Item extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", length = 20)
    private Long id;

    @Column(name = "item_name", length = 30)
    private String itemName;

    @Column(name = "price", length = 20)
    private Integer price;

    @Column(name = "stock_number", length = 20)
    private Integer stockNumber;

    @Lob
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_status", length = 10)
    private ItemStatus itemStatus;

    @Builder
    public Item(Long id, String itemName, Integer price, Integer stockNumber, String content, ItemStatus itemStatus) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.stockNumber = stockNumber;
        this.content = content;
        this.itemStatus = itemStatus;
    }

    static public Item mapToEntity(ItemCreateRequest request) {
        return Item.builder()
                .itemName(request.getItemName())
                .price(request.getPrice())
                .stockNumber(request.getStockNumber())
                .content(request.getContent())
                .itemStatus(ItemStatus.ON_SALE)
                .build();
    }

    public void update(ItemUpdateRequest request) {
        this.itemName = request.getItemName();
        this.price = request.getPrice();
        this.stockNumber = request.getStockNumber();
        this.content = request.getContent();
        this.itemStatus = request.getItemStatus();
    }

    public void updateStatus() {
        if(this.itemStatus == ItemStatus.ON_SALE) {
            this.itemStatus = ItemStatus.SOLD_OUT;
        } else {
            this.itemStatus = ItemStatus.ON_SALE;
        }
    }

}
