package com.newyear.present.repository;

import com.newyear.present.entity.ReadyPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReadyPackageRepo extends JpaRepository<ReadyPackage, Long> {

}
