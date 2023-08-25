package com.example.reactchallengestudybackend.domain.item.service;

import com.example.reactchallengestudybackend.common.exception.CustomException;
import com.example.reactchallengestudybackend.common.exception.ErrorCode;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemCreateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemSearchRequest;
import com.example.reactchallengestudybackend.domain.item.dto.request.ItemUpdateRequest;
import com.example.reactchallengestudybackend.domain.item.dto.response.ItemResponse;
import com.example.reactchallengestudybackend.domain.item.entity.Item;
import com.example.reactchallengestudybackend.domain.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    // 상품 생성
    @Transactional
    @Override
    public ItemResponse createItem(ItemCreateRequest request) {

        log.info("request: {}", request);

        Item item = Item.mapToEntity(request);
        log.info("item: {}", item);

        Item saveItem = itemRepository.save(item);
        log.info("saveItem: {}", saveItem);

        return ItemResponse.mapToDto(saveItem);
    }

    // 상품 단건 조회
    @Override
    public ItemResponse getItem(Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ITEM));
        log.info("findItem: {}", item);

        return ItemResponse.mapToDto(item);
    }

    // 상품 전체조회 (페이징, 검색)
    @Override
    public Page<ItemResponse> getItemList(Pageable pageable, ItemSearchRequest request) {

        Page<Item> itemList = itemRepository.getItemList(pageable, request);
        log.info("itemList: {}", itemList);

        return itemList.map(ItemResponse::mapToDto);
    }

    // 상품 수정
    @Transactional
    @Override
    public ItemResponse updateItem(Long itemId, ItemUpdateRequest request) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ITEM));
        log.info("findItem: {}", item);

        item.update(request);
        log.info("updateItem: {}", item);

        return ItemResponse.mapToDto(item);
    }

    // 상품 삭제
    @Transactional
    @Override
    public void deleteItem(Long itemId) {

        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new CustomException(ErrorCode.NOT_FOUND_ITEM));
        log.info("findItem: {}", item);

        itemRepository.delete(item);
    }
}
