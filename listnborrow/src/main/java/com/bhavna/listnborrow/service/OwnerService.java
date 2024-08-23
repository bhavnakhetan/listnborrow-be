package com.bhavna.listnborrow.service;

import com.bhavna.listnborrow.controller.OwnerController;
import com.bhavna.listnborrow.model.Owner;
import com.bhavna.listnborrow.repo.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerService {
    @Autowired
    private OwnerRepository ownerRepository;

    public List<Owner> getAllOwners(){
        return ownerRepository.findAll();
    }
}
