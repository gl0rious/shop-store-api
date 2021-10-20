package com.store.shop.service;

import com.store.shop.model.OrderItem;
import com.store.shop.model.OrderItemInfo;
import com.store.shop.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository itemRepo;

    public OrderItem findById(Long itemId) {
//        return itemRepo.findById(itemId).orElseThrow(
//                ()->new EntityNotFoundException(OrderItem.class, itemId)
//        );
        return itemRepo.findById(itemId).orElse(null);
    }

    public boolean ownedBy(Long userId, Long itemId) {
        OrderItem item = itemRepo.findById(itemId).orElse(null);
        return item!=null && item.getOrder().getUser().getId()!=userId;
    }

    public boolean exist(Long itemId) {
        return itemRepo.existsById(itemId);
    }

    public OrderItem updateFromInfo(OrderItemInfo itemInfo) {
        OrderItem item = findById(itemInfo.getItemId());
        item.setQuantity(itemInfo.getQuantity());
        item = update(item);
        return item;
    }

    private OrderItem update(OrderItem item) {
        return itemRepo.save(item);
    }
}