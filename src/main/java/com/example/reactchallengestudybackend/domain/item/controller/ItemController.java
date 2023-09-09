package com.example.reactchallengestudybackend.domain.item.controller;

import com.example.reactchallengestudybackend.domain.item.dto.request.ItemCreateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemSearchRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemUpdateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.response.ItemResponse;
import com.example.reactchallengestudybackend.domain.item.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // 상품 생성
    @PostMapping("/api/items")
    public ResponseEntity<ItemResponse> createItem(@Valid @RequestBody ItemCreateRequest request) {
        log.info("ItemController createItem() run");

        ItemResponse createItem = itemService.createItem(request);
        log.info("createItem: {}", createItem);

        return ResponseEntity.ok(createItem);
    }

    // 상품 단건 조회
    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<ItemResponse> getItem(@PathVariable Long itemId) {
        log.info("ItemController getItem() run");

        ItemResponse item = itemService.getItem(itemId);
        log.info("item: {}", item);

        return ResponseEntity.ok(item);
    }

    // 상품 전체조회 (페이징, 검색)
    @GetMapping("/api/items")
    public ResponseEntity<Page<ItemResponse>> getItemList(@PageableDefault(page = 0, size = 10) Pageable pageable,
                                                          ItemSearchRequest request) {
        log.info("ItemController getItemList() run");

        Page<ItemResponse> itemList = itemService.getItemList(pageable, request);

        return ResponseEntity.ok(itemList);
    }

    // 상품 전체조회 list
    @GetMapping("/api/items/list")
    public ResponseEntity<List<ItemResponse>> getItems() {
        log.info("ItemController getItems() run");

        List<ItemResponse> itemlist = itemService.getItems();

        return ResponseEntity.ok(itemlist);
    }


    // 상품 수정
    @PatchMapping("/api/items/{itemId}")
    public ResponseEntity<ItemResponse> updateItem(@PathVariable Long itemId,
                                                   @RequestBody ItemUpdateRequest request) {
        log.info("ItemController updateItem() run");

        ItemResponse updateItem = itemService.updateItem(itemId, request);
        log.info("updateItem: {}", updateItem);

        return ResponseEntity.ok(updateItem);
    }

    // 상품 상태변경
    @PatchMapping("/api/items/status/{itemId}")
    public void updateItemStatus(@PathVariable Long itemId) {
        log.info("ItemController updateItemStatus() run");

        itemService.updateItemStatus(itemId);
    }

    // 상품 삭제
    @DeleteMapping("/api/items/{itemId}")
    public void deleteItem(@PathVariable Long itemId) {
        log.info("ItemController deleteItem() run");

        itemService.deleteItem(itemId);
    }
}
