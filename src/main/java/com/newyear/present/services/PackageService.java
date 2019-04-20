package com.newyear.present.services;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.sweets.Sweets;

import java.util.List;

public interface PackageService {

    List<ReadyPackage> findAll();

    ReadyPackage getOne(Long id);

    <S extends ReadyPackage> S save(S s);

    void showAllReadyPackages();

    Long getWeight(Long id);

    void showById(Long id);

    void updateWeight(Long id);

    List<Sweets> sortPackageBySweetsWeight(Long id);

    List<Sweets> findSweetsBySugarDiapason(Long start, Long end,Long id);


}
