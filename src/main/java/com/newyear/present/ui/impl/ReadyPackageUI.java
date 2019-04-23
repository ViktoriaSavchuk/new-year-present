package com.newyear.present.ui.impl;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.services.PackageService;
import com.newyear.present.services.SweetsPackagesService;
import com.newyear.present.ui.ConsoleUI;
import com.newyear.present.ui.PackageCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Log4j2
@Component
@Transactional
public class ReadyPackageUI extends ConsoleUI implements PackageCreator {

    @Autowired
    PackageService packageService;

    @Autowired
    SweetsPackagesService sweetsPackagesService;

    @Override
    public ReadyPackage createPackage(StringBuilder decision) {

        System.out.println(sweetsPackagesService.findAll());

        long packageId = 0;

        System.out.println("Choose package by id");
        packageService.showAllReadyPackages();

        try {
            packageId = scanner.nextLong();
            Date date = packageService.getOne(packageId).getDate();
        } catch (Exception e) {
            System.out.println("Do you want to exit without new year package? {Yes/no)");
            if (yesOrNo(decision)) {
                return null;
            } else {
                run();
            }
        }

        return packageService.getOne(packageId);
    }
}
