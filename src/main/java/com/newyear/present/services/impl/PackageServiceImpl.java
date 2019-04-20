package com.newyear.present.services.impl;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.entity.sweets.Sweets;
import com.newyear.present.repository.ReadyPackageRepo;
import com.newyear.present.services.PackageService;
import com.newyear.present.services.SweetsPackagesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PackageServiceImpl implements PackageService {

    private final ReadyPackageRepo readyPackageRepo;
    private final SweetsPackagesService sweetsPackagesService;

    @Override
    public List<ReadyPackage> findAll() {
        return readyPackageRepo.findAll();
    }

    @Override
    public ReadyPackage getOne(Long aLong) {
        return readyPackageRepo.getOne(aLong);
    }

    @Override
    public <S extends ReadyPackage> S save(S s) {
        return readyPackageRepo.save(s);
    }

    @Override
    public void updateWeight(Long id) {
        long weight = sweetsPackagesService
                .findAll()
                .stream()
                .filter(sweet -> sweet.getReadyPackageByPackageId().getId() == id).
                        mapToLong(row ->
                                (row.getConsumerSweetsByConsumerSweetId() != null
                                        && row.getConsumerSweetsByConsumerSweetId().getWrapperWeight() != null ?
                                        row.getConsumerSweetsByConsumerSweetId().getWrapperWeight() : 0) +
                                        (row.getConsumerSweetsByConsumerSweetId() != null
                                                && row.getConsumerSweetsByConsumerSweetId().getFillingWeight() != null ?
                                                row.getConsumerSweetsByConsumerSweetId().getFillingWeight() : 0) +
                                        (row.getSellerSweetsBySellerSweetId() != null
                                                && row.getSellerSweetsBySellerSweetId().getWrapperWeight() != null ?
                                                row.getSellerSweetsBySellerSweetId().getWrapperWeight() : 0) +
                                        (row.getSellerSweetsBySellerSweetId() != null
                                                && row.getSellerSweetsBySellerSweetId().getFillingWeight() != null ?
                                                row.getSellerSweetsBySellerSweetId().getFillingWeight() : 0)).sum();

        readyPackageRepo.getOne(id).setWeight(weight);
    }


    @Override
    public void showAllReadyPackages() {
        System.out.println(findAll().toString()
                .replaceAll(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    @Override
    public Long getWeight(Long id) {
        ReadyPackage readyPackage = readyPackageRepo.findById(id).orElseThrow(() -> new RuntimeException());
        return readyPackage.getWeight();
    }

    @Override
    public void showById(Long id) {
        System.out.println(getOne(id).toString()
                .replaceAll(",", "")
                .replace("[", "")
                .replace("]", ""));
    }

    @Override
    public List<Sweets> sortPackageBySweetsWeight(Long id) {

        List<Sweets> sweets = allSweetsInPackage(id);

        sweets.sort(new Comparator<Sweets>() {
            @Override
            public int compare(Sweets o1, Sweets o2) {
                return (int) (((o2.getFillingWeight() != null ? o2.getFillingWeight() : 0)

                        + ((o2.getWrapperWeight()) != null ? o2.getWrapperWeight() : 0))

                        - ((o1.getWrapperWeight() != null ? o1.getWrapperWeight() : 0) + (o1.getFillingWeight() != null ? o1.getFillingWeight() : 0)));
            }
        });

        return sweets;
    }


    @Override
    public List<Sweets> findSweetsBySugarDiapason(Long start, Long end, Long id) {

        List<Sweets> sweets = allSweetsInPackage(id);
        List<Sweets> suitableSweets = new ArrayList<>();

        for (Sweets sweet : sweets) {
            double sugarInSweet = ((sweet.getFillingWeight() != null ? sweet.getFillingWeight() : 0)
                    * (sweet.getSweetFillingByFillingId() != null
                    ? sweet.getSweetFillingByFillingId().getSugarIn1000Mg() : 0)
                    +
                    (sweet.getWrapperWeight() != null ? sweet.getWrapperWeight() : 0) *
                            (sweet.getSweetWrapperByWrapperId() != null
                                    ? sweet.getSweetWrapperByWrapperId().getSugarIn1000Mg() : 0))
                    / 1000;
            if (sugarInSweet >= start && sugarInSweet <= end) {
                suitableSweets.add(sweet);
            }

        }
        return suitableSweets;
    }

    public List<Sweets> allSweetsInPackage(Long id) {
        List<Sweets> sweets = new ArrayList<>();

        for (SweetsPackages swp : sweetsPackagesService.findAll()) {

            long id1 = swp.getReadyPackageByPackageId().getId();
            if (id1 == id) {
                if (swp.getSellerSweetsBySellerSweetId() != null)
                    sweets.add(swp.getSellerSweetsBySellerSweetId());

                if (swp.getConsumerSweetsByConsumerSweetId() != null)
                    sweets.add(swp.getConsumerSweetsByConsumerSweetId());
            }
        }

        return sweets;
    }


}
