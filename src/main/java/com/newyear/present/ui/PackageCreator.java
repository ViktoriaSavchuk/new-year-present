package com.newyear.present.ui;

import com.newyear.present.entity.ReadyPackage;

public interface PackageCreator {

    ReadyPackage createPackage(StringBuilder decision);
}
