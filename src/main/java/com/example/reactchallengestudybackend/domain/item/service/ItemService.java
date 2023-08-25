package com.example.reactchallengestudybackend.domain.item.service;

import com.example.reactchallengestudybackend.domain.item.dto.request.ItemCreateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemSearchRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemUpdateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.response.ItemResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemService {

    // 상품 생성
    ItemResponse createItem(ItemCreateRequest request);

    // 상품 단건 조회
    ItemResponse getItem(Long itemId);

    // 상품 전체조회 (페이징, 검색)
    Page<ItemResponse> getItemList(Pageable pageable, ItemSearchRequest request);

    // 상품 수정
    ItemResponse updateItem(Long itemId, ItemUpdateRequest request);

    // 상품 삭제
    void deleteItem(Long itemId);
}
