package com.newyear.present.repository;

import com.newyear.present.entity.sweets.SellerSweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerSweetsRepo extends JpaRepository<SellerSweet, Long> {

}
