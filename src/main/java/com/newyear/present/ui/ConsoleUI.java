package com.newyear.present.ui;

import com.newyear.present.entity.ReadyPackage;
import com.newyear.present.entity.sweets.Sweets;
import com.newyear.present.services.PackageService;
import com.newyear.present.ui.impl.PackageWithCustomSweetsUI;
import com.newyear.present.ui.impl.PackageWithSellerSweetsUI;
import com.newyear.present.ui.impl.ReadyPackageUI;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Log4j2
@Component
public class ConsoleUI {

    @Autowired
    private ReadyPackageUI readyPackageUI;

    @Autowired
    private PackageWithSellerSweetsUI packageWithSellerSweetsUI;

    @Autowired
    private PackageWithCustomSweetsUI packageWithCustomSweetsUI;

    @Autowired
    PackageService packageService;


    protected Scanner scanner = new Scanner(System.in);

    Long packageId = null;

    public void run() {

        StringBuilder decision = new StringBuilder();

        if (!greeting(decision)) System.exit(0);

        packageId = createPackage(decision);
        if (packageId != null) {
            System.out.println("your package");
            packageService.showById(packageId);
            List<Sweets> sweets = packageService.sortPackageBySweetsWeight(packageId);

            System.out.println(sweets.toString());
            if(packageService.findSweetsBySugarDiapason(0L, 10000L, packageId)!=null){
                System.out.println("Sweets in which the amount of sugar is within the specified limits");
                System.out.println(packageService.findSweetsBySugarDiapason(0L, 10000L, packageId));
            }
        }
        System.exit(0);
    }


    public boolean greeting(StringBuilder decision) {

        System.out.println("Hello! Do you want to choose a sweet new year present? (Yes/no)");
        return yesOrNo(decision);
    }

    private Long createPackage(StringBuilder decision) {

        System.out.println("Choose ready package (1)\n" +
                "Create a package from the seller sweets (2) \n" +
                "create sweets by yourself (3) ");
        decision.replace(0, decision.length(), scanner.next());

        switch (decision.toString()) {
            case "1": {
                return readyPackageUI.createPackage(decision).getId();
            }
            case "2": {
                return packageWithSellerSweetsUI.createPackage(decision).getId();
            }
            case "3":
                return packageWithCustomSweetsUI.createPackage(decision).getId();

            default:
                run();
        }
        return null;
    }


    public void getChosenPackage(ReadyPackage readyPackage) {
        System.out.println("Your package");
        System.out.println(readyPackage.toString());
        System.out.println();
        System.exit(0);
    }


    public boolean yesOrNo(StringBuilder decision) {

        decision.replace(0, decision.length(), scanner.next());

        return decision.toString().equalsIgnoreCase("y")
                || decision.toString().equalsIgnoreCase("Yes");
    }

    public boolean toClarify(StringBuilder decision) {
        decision.replace(0, decision.length(), scanner.next());
        if (yesOrNo(decision)) {
            return false;
        }
        return true;
    }

}
