package com.bhavna.listnborrow.controller;

import com.bhavna.listnborrow.model.Items;
import com.bhavna.listnborrow.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping
    public List<Items> getAllItems() {
        return itemService.getAllItems();
    }
}