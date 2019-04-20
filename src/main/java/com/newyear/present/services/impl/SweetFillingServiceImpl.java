package com.newyear.present.services.impl;

import com.newyear.present.entity.SweetFilling;
import com.newyear.present.repository.SweetFillingsRepo;
import com.newyear.present.services.SweetFillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SweetFillingServiceImpl implements SweetFillingService {

    @Autowired
    SweetFillingsRepo sweetFillingsRepo;

    @Override
    public SweetFilling getOne(Long fillingsId) {
        return sweetFillingsRepo.getOne(fillingsId);
    }

    @Override
    public List<SweetFilling> findAll() {
        return sweetFillingsRepo.findAll();
    }

    @Override
    public void showAll() {
        System.out.println(findAll()
                .toString()
                .replaceAll(",", "")
                .replace("[", "")
                .replace("]", ""));
    }
}
