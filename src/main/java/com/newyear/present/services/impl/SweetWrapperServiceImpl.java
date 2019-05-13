package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetWrapper;
import com.newyear.present.repository.SweetWrappersRepo;
import com.newyear.present.services.SweetWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SweetWrapperServiceImpl implements SweetWrapperService {

    @Autowired
    SweetWrappersRepo sweetWrappersRepo;

    @Override
    public List<SweetWrapper> findAll() {
        return sweetWrappersRepo.findAll();
    }

    @Override
    public SweetWrapper getOne(Long sweetWrapperId) {
        return sweetWrappersRepo.getOne(sweetWrapperId);
    }

    @Override
    public void showAll() {
        System.out.println(findAll().toString()
                .replaceAll(",", "")
                .replace("[", "")
                .replace("]", ""));
    }
}
