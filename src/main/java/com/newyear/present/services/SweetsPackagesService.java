package com.newyear.present.services;

import com.newyear.present.entity.SweetsPackages;

import java.util.List;

public interface SweetsPackagesService {

    List<SweetsPackages> findAll();

    SweetsPackages getOne(Long id);

    <S extends SweetsPackages> S save(S s);

    void showById(Long id);

}
