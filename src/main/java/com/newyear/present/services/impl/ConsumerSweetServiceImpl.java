package com.newyear.present.services.impl;

import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.repository.ConsumerSweetsRepo;
import com.newyear.present.services.ConsumerSweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsumerSweetServiceImpl implements ConsumerSweetService {

    @Autowired
    ConsumerSweetsRepo consumerSweetsRepo;

    @Override
    public List<ConsumerSweet> findAll() {
        return consumerSweetsRepo.findAll();
    }

    @Override
    public ConsumerSweet getOne(Long consumerSweetId) {
        return consumerSweetsRepo.getOne(consumerSweetId);
    }

    @Override
    public ConsumerSweet save(ConsumerSweet consumerSweet) {
        return consumerSweetsRepo.save(consumerSweet);
    }

}
