package com.newyear.present.ui.impl;


import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.SweetsPackages;
import com.newyear.present.entity.sweets.ConsumerSweet;
import com.newyear.present.services.*;
import com.newyear.present.ui.ConsoleUI;
import com.newyear.present.ui.PackageCreator;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Log4j2
@Component
@Transactional
public class PackageWithCustomSweetsUI extends ConsoleUI implements PackageCreator {

    @Autowired
    private SweetFillingService sweetFillingService;

    @Autowired
    private SweetWrapperService sweetWrapperService;

    @Autowired
    private ConsumerSweetService consumerSweetService;

    @Autowired
    private SweetsPackagesService sweetsPackagesService;

    @Autowired
    private PackageService packageService;

    @Override
    public ReadyPackage createPackage(StringBuilder decision) {


        int sweetsQuantity = 0;
        boolean continueChoose = true;
        int lineLength = 0;

        StringBuilder type = new StringBuilder();

        ReadyPackage readyPackage = ReadyPackage.builder()
                .date(new Date(System.currentTimeMillis()))
                .createdBy("consumer")
                .build();

        packageService.save(readyPackage);


        System.out.println("How much sweets do you want to create?");

        do {
            try {
                lineLength = 0;
                sweetsQuantity = scanner.nextInt();
                if (sweetsQuantity < 1) throw new Exception();
                lineLength = Integer.toString(sweetsQuantity).length();
                continueChoose = false;
            } catch (Exception e) {
                System.out.println("Do you want to end? {Yes/no)");
                continueChoose = toClarify(decision);
            }
        } while (continueChoose & lineLength > 0);

        for (int i = 1; i <= sweetsQuantity; i++) {

            ConsumerSweet consumerSweet = new ConsumerSweet();

            Long fillingId = null;
            Long wrapperId = null;
            Long fillingWeight = null;
            Long wrapperWeight = null;

            type.replace(0, type.length(), "");

            System.out.println("Sweet #" + i);
            System.out.println("Do you want to choose filling for your sweet");

            if (yesOrNo(decision)) {
                sweetFillingService.showAll();

                try {
                    lineLength = 0;
                    fillingId = scanner.nextLong();
                    lineLength = Long.toString(fillingId).length();
                    if (fillingId < 1 && lineLength < 1) throw new Exception();

                    System.out.println("input weight of filling in mg");
                    lineLength = 0;
                    fillingWeight = scanner.nextLong();
                    lineLength = Long.toString(fillingWeight).length();
                    if (fillingWeight < 1 && lineLength < 1) throw new Exception();

                    type.append(sweetFillingService.getOne(fillingId).getType());

                    consumerSweet.setSweetFillingByFillingId(sweetFillingService.getOne(fillingId));
                    consumerSweet.setFillingWeight(fillingWeight);

                } catch (Exception e) {
                    System.out.println("Do you want to end? {Yes/no)");
                }
            }


            boolean needWrapper = true;
            if (fillingId != null && fillingId > 0) {
                System.out.println("Do you want to choose wrapper for your sweet");
                needWrapper = yesOrNo(decision);

                if (needWrapper) type.append("_");
            }
            if (needWrapper) {

                System.out.println("So, choose wrapper for your sweet");
                sweetWrapperService.showAll();

                try {
                    lineLength = 0;
                    wrapperId = scanner.nextLong();
                    lineLength = Long.toString(wrapperId).length();
                    if (wrapperId < 1 && lineLength < 1) throw new Exception();

                    System.out.println("input weight of wrapper in mg");
                    lineLength = 0;
                    wrapperWeight = scanner.nextLong();
                    lineLength = Long.toString(wrapperWeight).length();
                    if (wrapperWeight < 1 && lineLength < 1) throw new Exception();

                    type.append(sweetWrapperService.getOne(wrapperId).getType());

                    consumerSweet.setSweetWrapperByWrapperId(sweetWrapperService.getOne(wrapperId));
                    consumerSweet.setWrapperWeight(wrapperWeight);

                } catch (Exception e) {
                    System.out.println("Do you want to end? {Yes/no)");
                }
            }

            consumerSweet.setName("customer_sweet_" + new Date(System.currentTimeMillis()));
            consumerSweet.setType(type.toString());

            consumerSweetService.save(consumerSweet);

            System.out.println(consumerSweet.toString());

            sweetsPackagesService.save(
                    SweetsPackages.builder()
                            .readyPackageByPackageId(readyPackage)
                            .consumerSweetsByConsumerSweetId(consumerSweetService.getOne(consumerSweet.getId()))
                            .build());

            packageService.updateWeight(readyPackage.getId());
            packageService.save(readyPackage);
        }

        return readyPackage;
    }
}
