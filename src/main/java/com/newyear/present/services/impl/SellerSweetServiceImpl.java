package com.newyear.present.services.impl;

import com.newyear.present.entity.sweets.SellerSweet;
import com.newyear.present.repository.SellerSweetsRepo;
import com.newyear.present.services.SellerSweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SellerSweetServiceImpl implements SellerSweetService {

    @Autowired
    SellerSweetsRepo sellerSweetsRepo;

    @Override
    public List<SellerSweet> findAll() {
        return sellerSweetsRepo.findAll();
    }

    @Override
    public SellerSweet getOne(Long sellerSweetId) {
        return sellerSweetsRepo.getOne(sellerSweetId);
    }

    @Override
    public void showAllSellerSweets() {
        System.out.println(findAll()
                .toString()
                .replaceAll(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

}
