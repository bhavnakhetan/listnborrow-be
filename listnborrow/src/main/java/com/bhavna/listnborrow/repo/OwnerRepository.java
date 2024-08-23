package com.bhavna.listnborrow.repo;

import com.bhavna.listnborrow.model.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepository extends JpaRepository <Owner, Long> {

}
