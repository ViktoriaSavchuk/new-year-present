package com.newyear.present.services;

import com.newyear.present.entity.SweetFilling;

import java.util.List;

public interface SweetFillingService {

    SweetFilling getOne(Long fillingsId);

    List<SweetFilling> findAll();

    void showAll();

}
