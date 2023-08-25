package com.example.reactchallengestudybackend.domain.item.repository;

import com.example.reactchallengestudybackend.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>,
                                        ItemRepositoryCustom {
}
