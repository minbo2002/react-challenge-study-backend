package com.example.reactchallengestudybackend.domain.item.repository;

import com.example.reactchallengestudybackend.domain.item.dto.request.ItemSearchRequest;
import com.example.reactchallengestudybackend.domain.item.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getItemList(Pageable pageable, ItemSearchRequest searchRequest);
}
