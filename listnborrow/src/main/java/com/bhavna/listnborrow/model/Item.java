package com.bhavna.listnborrow.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Item{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="item_name")
    private String itemName;
    @Column(name="item_description")
    private String itemDescription;
    @Column(name="amount_charged")
    private Float amountCharged;
    @ManyToOne()
    @JoinColumn(name="ownerId", nullable=false)
    @JsonManagedReference
    private Owner owner;
    @Column(name="borrowed")
    private Boolean borrowed;
    @Temporal(TemporalType.DATE)
    @Column(name="dateadded")
    private Date dateAdded;

    @PrePersist
    protected  void onCreate(){
        this.dateAdded=new Date();
    }

    public Item() {
    }

    public Item(Long id, String itemName, String itemDescription, Float amountCharged, Owner owner, Boolean borrowed, Date dateAdded) {
        this.id = id;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.amountCharged = amountCharged;
        this.owner = owner;
        this.borrowed = borrowed;
        this.dateAdded = dateAdded;
    }

    public Item(String itemName, String itemDescription, Float amountCharged, Owner owner, Boolean borrowed, Date dateAdded) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.amountCharged = amountCharged;
        this.owner = owner;
        this.borrowed = borrowed;
        this.dateAdded = dateAdded;
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

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Boolean getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(Boolean borrowed) {
        this.borrowed = borrowed;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}