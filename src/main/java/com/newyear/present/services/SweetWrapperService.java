package com.newyear.present.services;

import com.newyear.present.entity.SweetWrapper;

import java.util.List;
import java.util.Optional;

public interface SweetWrapperService {

    List<SweetWrapper> findAll();

    SweetWrapper getOne(Long sweetWrapperId);

    void showAll();

}
