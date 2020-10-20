package com.springboot.orderprocessing.service.impl;

import com.springboot.orderprocessing.model.Item;
import com.springboot.orderprocessing.repository.ItemRepository;
import com.springboot.orderprocessing.service.ItemService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> createItems(List<Item> items) {
        return (List<Item>) itemRepository.saveAll(items);
    }

    @Override
    public List<Item> findAllItems() {
        final List<Item> item = new ArrayList<>();
        itemRepository.findAll().forEach(item::add);
        return item;
    }

    @Override
    public Optional<Item> findByItemId(String itemId) {
        return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> findAllByIds(List<String> ids) {
        return (List<Item>) itemRepository.findAllById(ids);
    }
}
