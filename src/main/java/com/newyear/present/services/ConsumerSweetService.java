package com.newyear.present.services;

import com.newyear.present.entity.sweets.ConsumerSweet;

import java.util.List;

public interface ConsumerSweetService {

    List<ConsumerSweet> findAll();

    ConsumerSweet getOne(Long consumerSweetId);

    ConsumerSweet save(ConsumerSweet consumerSweet);

}
