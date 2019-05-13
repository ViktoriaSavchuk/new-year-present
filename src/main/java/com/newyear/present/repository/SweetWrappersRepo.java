package com.newyear.present.repository;

import com.newyear.present.entity.SweetWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SweetWrappersRepo extends JpaRepository<SweetWrapper, Long> {


}
