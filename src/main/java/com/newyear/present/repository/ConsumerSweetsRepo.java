package com.newyear.present.repository;

import com.newyear.present.entity.sweets.ConsumerSweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConsumerSweetsRepo extends JpaRepository<ConsumerSweet, Long> {


}
