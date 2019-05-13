package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.repository.SweetsPackagesRepo;
import com.newyear.present.services.SweetsPackagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SweetsPackagesServiceImpl implements SweetsPackagesService {

    @Autowired
    SweetsPackagesRepo sweetsPackagesRepo;

    @Override
    public List<SweetsPackages> findAll() {
        return sweetsPackagesRepo.findAll();
    }


    @Override
    public SweetsPackages getOne(Long id) {
        return sweetsPackagesRepo.getOne(id);
    }

    @Override
    public <S extends SweetsPackages> S save(S s) {
        return sweetsPackagesRepo.save(s);
    }

    @Override
    public void showById(Long id) {
        System.out.println(getOne(id).toString());
    }

}
