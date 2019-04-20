package com.newyear.present.repository;

import com.newyear.present.entity.SweetsPackages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SweetsPackagesRepo extends JpaRepository<SweetsPackages, Long> {

    //List<SweetsPackages>findAllByPackageId(@Param("package_id")Long id);


}
