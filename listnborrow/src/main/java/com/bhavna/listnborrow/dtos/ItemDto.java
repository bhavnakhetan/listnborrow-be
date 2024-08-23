package com.bhavna.listnborrow.dtos;

import com.bhavna.listnborrow.model.Owner;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

public class ItemDto {
    private Long id;
    @NotEmpty(message="Name cannot be empty")
    private String itemName;
    private String itemDescription;
    private Float amountCharged;
    private Long ownerId;
    private Boolean borrowed;

    public ItemDto(String itemName, String itemDescription, Float amountCharged, Long ownerId, Boolean borrowed) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.amountCharged = amountCharged;
        this.ownerId = ownerId;
        this.borrowed = borrowed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public Float getAmountCharged() {
        return amountCharged;
    }

    public void setAmountCharged(Float amountCharged) {
        this.amountCharged = amountCharged;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }
}
