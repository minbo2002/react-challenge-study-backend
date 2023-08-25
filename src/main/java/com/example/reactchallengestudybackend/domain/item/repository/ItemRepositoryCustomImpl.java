package com.example.reactchallengestudybackend.domain.item.repository;

import com.example.reactchallengestudybackend.domain.item.dto.request.ItemSearchRequest;
import com.example.reactchallengestudybackend.domain.item.entity.Item;
import com.example.reactchallengestudybackend.domain.item.entity.ItemStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import java.util.List;
import static com.example.reactchallengestudybackend.domain.item.entity.QItem.*;

@RequiredArgsConstructor
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Item> getItemList(Pageable pageable, ItemSearchRequest searchRequest) {

        List<Item> itemList = queryFactory
                .selectFrom(item)
                .where(eqItemName(searchRequest.getItemName()),
                        eqPrice(searchRequest.getPrice()),
                        eqContent(searchRequest.getContent()),
                        eqItemStatus(searchRequest.getItemStatus()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(item.id.asc())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(item.count())
                .from(item)
                .where(eqItemName(searchRequest.getItemName()),
                        eqPrice(searchRequest.getPrice()),
                        eqContent(searchRequest.getContent()),
                        eqItemStatus(searchRequest.getItemStatus()));

        return PageableExecutionUtils.getPage(itemList, pageable, countQuery::fetchOne);
    }

    private BooleanExpression eqItemName(String itemName) {
        if (itemName == null || itemName.isEmpty()) {
            return null;
        }
        return item.itemName.like("%"+itemName+"%");
    }

    private BooleanExpression eqPrice(Integer price) {
        if (price == null) {
            return null;
        }
        return item.price.eq(price);
    }

    private BooleanExpression eqContent(String content) {
        if (content == null || content.isEmpty()) {
            return null;
        }
        return item.content.like("%"+content+"%");
    }

    private BooleanExpression eqItemStatus(ItemStatus itemStatus) {
        if (itemStatus == null) {
            return null;
        }
        return item.itemStatus.eq(itemStatus);
    }
}
