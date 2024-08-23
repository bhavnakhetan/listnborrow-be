package com.bhavna.listnborrow.repo;

import com.bhavna.listnborrow.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query ("SELECT i FROM Item i WHERE amountCharged= amountCharged")
    List<Item> findItemsByAmount(Float amountCharged);
}