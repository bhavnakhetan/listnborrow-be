package com.bhavna.listnborrow.service;

import com.bhavna.listnborrow.model.Items;
import com.bhavna.listnborrow.repo.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    public List<Items> getAllItems() {
        return itemRepository.findAll();
    }
}