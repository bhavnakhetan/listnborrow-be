package com.bhavna.listnborrow.controller;

import com.bhavna.listnborrow.dtos.ItemDto;
import com.bhavna.listnborrow.exceptions.BadRequestException;
import com.bhavna.listnborrow.exceptions.InvalidOwnerIdException;
import com.bhavna.listnborrow.model.Item;
import com.bhavna.listnborrow.model.Owner;
import com.bhavna.listnborrow.repo.ItemRepository;
import com.bhavna.listnborrow.repo.OwnerRepository;
import com.bhavna.listnborrow.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

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
    public ResponseEntity<List<Item>> getAllItems(@RequestParam(value = "amount", required = false) Float amountCharged) {
        List<Item> items;
        if (amountCharged != null)
            items= itemService.getAllItemsByAmount(amountCharged);
        else
            items= itemService.getAllItems();
        return ResponseEntity.ok(items);
    }
//// How to call this from UI
// URL without query parameter
//axios.get('/api/items')
//        .then(response => {
//        console.log(response.data); // Handle the response data
//    })
//            .catch(error => {
//        console.error('Error fetching items:', error);
//    });
//
//// URL with query parameter
//axios.get('/api/items', { params: { amount: 10.5 } })
//            .then(response => {
//        console.log(response.data); // Handle the response data
//    })
//            .catch(error => {
//        console.error('Error fetching items with amount:', error);
//    });
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
        if (itemDto.getId()!=null)
            throw new BadRequestException("Cannot set ID while creating Item");
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
        boolean isExists = itemService.doesIdExist(id);
        if (isExists){
            boolean deleted = itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

}