package com.newyear.present.services;

import com.newyear.present.entity.sweets.SellerSweet;

import java.util.List;

public interface SellerSweetService {

    List<SellerSweet> findAll();

    SellerSweet getOne(Long sellerSweetId);

    void showAllSellerSweets();


}
