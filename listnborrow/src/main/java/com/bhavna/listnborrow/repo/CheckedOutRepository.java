package com.bhavna.listnborrow.repo;

import com.bhavna.listnborrow.model.CheckedOut;
import com.bhavna.listnborrow.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckedOutRepository extends JpaRepository<CheckedOut , Long>{
}