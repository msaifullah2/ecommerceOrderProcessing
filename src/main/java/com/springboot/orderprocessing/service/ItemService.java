package com.springboot.orderprocessing.service;

import com.springboot.orderprocessing.model.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface ItemService {

    public Item createItem(Item item);

    public List<Item> createItems(List<Item> items);

    public List<Item> findAllItems();

    public Optional<Item> findByItemId(String itemId);

    public List<Item> findAllByIds(List<String> ids);
}
