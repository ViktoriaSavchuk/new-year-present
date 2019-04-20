package com.newyear.present.ui.impl;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.services.PackageService;
import com.newyear.present.services.SellerSweetService;
import com.newyear.present.services.SweetsPackagesService;
import com.newyear.present.ui.ConsoleUI;
import com.newyear.present.ui.PackageCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Log4j2
@Service
@Transactional
public class PackageWithSellerSweetsUI extends ConsoleUI implements PackageCreator {

    @Autowired
    SellerSweetService sellerSweetService;

    @Autowired
    PackageService packageService;

    @Autowired
    SweetsPackagesService sweetsPackagesService;

    @Override
    public ReadyPackage createPackage(StringBuilder decision) {
        long sweetId = 0;

        ReadyPackage readyPackage = ReadyPackage.builder()
                .date(new Date(System.currentTimeMillis()))
                .createdBy("consumer")
                .build();

        packageService.save(readyPackage);

        System.out.println(readyPackage.getId());

        System.out.println("choose sweets by its id");
        sellerSweetService.showAllSellerSweets();

        do try {
            sweetId = scanner.nextLong();

            if(sellerSweetService.getOne(sweetId).getWrapperWeight() == null)
                throw new Exception();
            sweetsPackagesService.save(
                    SweetsPackages.builder()
                            .readyPackageByPackageId(readyPackage)
                            .sellerSweetsBySellerSweetId(sellerSweetService.getOne(sweetId))
                            .build());

        } catch (Exception e) {
            break;

        } while (sweetId>0);

        packageService.updateWeight(readyPackage.getId());
        packageService.save(readyPackage);

        System.out.println(readyPackage.getWeight());

        return readyPackage;
    }

}
