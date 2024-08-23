package com.bhavna.listnborrow.service;

import com.bhavna.listnborrow.model.Item;
import com.bhavna.listnborrow.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    public Item getItemById(Long id) {
        return itemRepository.findById(id).orElse(null);
    }

    public Item updateItem(Long id, Item updatedItem) {
        Item item = itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Item Id"));
        return  itemRepository.save(updatedItem);
    }

    public boolean deleteItem(Long id) {
        Item item= itemRepository.findById(id).orElseThrow(()->new IllegalArgumentException("Invalid Item Id"));
        itemRepository.delete(item);
        return true;
    }

    public List<Item> getAllItemsByAmount(Float amountCharged) {
        return itemRepository.findItemsByAmount(amountCharged);
    }
}