package com.bhavna.listnborrow.controller;

import com.bhavna.listnborrow.dtos.ItemDto;
import com.bhavna.listnborrow.exceptions.InvalidOwnerIdException;
import com.bhavna.listnborrow.model.Item;
import com.bhavna.listnborrow.model.Owner;
import com.bhavna.listnborrow.repo.ItemRepository;
import com.bhavna.listnborrow.repo.OwnerRepository;
import com.bhavna.listnborrow.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OwnerRepository ownerRepository;

    @GetMapping
    public List<Item> getAllItems(@RequestParam(value = "amount", required = false) Float amountCharged) {
        if (amountCharged != null) return itemService.getAllItemsByAmount(amountCharged);
        else return itemService.getAllItems();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable Long id){
        Item item= itemService.getItemById(id);
        return item != null ? ResponseEntity.ok(item): ResponseEntity.notFound().build();
    }

    @PutMapping ("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item updatedItem){
        Item item = itemService.updateItem(id, updatedItem);
        return item!=null ? ResponseEntity.ok(item) : ResponseEntity.notFound().build();
    }

    @PostMapping()
    public ResponseEntity<Item> createItem(@Valid @RequestBody ItemDto itemDto) {
        Owner owner= ownerRepository.findById(itemDto.getOwnerId()).orElseThrow(()->new InvalidOwnerIdException ("Invalid Owner Id"));
        Item item=new Item();
        item.setItemName(itemDto.getItemName());
        item.setItemDescription(itemDto.getItemDescription());
        item.setAmountCharged(itemDto.getAmountCharged());
        item.setOwner(owner);
        item.setBorrowed((itemDto.getBorrowed()));
        Item savedItem = itemService.createItem(item);
        return ResponseEntity.status(201).body(savedItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteItem(@PathVariable Long id){
        boolean deleted = itemService.deleteItem(id);
        return deleted? ResponseEntity.ok().build(): ResponseEntity.notFound().build();
    }

}