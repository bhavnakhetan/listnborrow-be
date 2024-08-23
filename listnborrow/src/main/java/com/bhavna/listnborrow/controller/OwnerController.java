package com.bhavna.listnborrow.controller;

import com.bhavna.listnborrow.model.Item;
import com.bhavna.listnborrow.model.Owner;
import com.bhavna.listnborrow.service.ItemService;
import com.bhavna.listnborrow.service.OwnerService;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/owners")
public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners(){
        return ownerService.getAllOwners();
    }

}
