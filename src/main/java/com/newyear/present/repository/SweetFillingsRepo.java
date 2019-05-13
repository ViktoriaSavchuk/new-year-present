package com.newyear.present.repository;


import com.newyear.present.entity.SweetFilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SweetFillingsRepo extends JpaRepository<SweetFilling, Long> {


}
