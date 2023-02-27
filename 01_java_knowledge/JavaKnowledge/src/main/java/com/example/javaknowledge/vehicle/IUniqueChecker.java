package com.example.javaknowledge.vehicle;

import java.util.List;

public interface IUniqueChecker extends IVehicleChecker {
    CheckResult checkUnique(List<IVehicle> underCheckItems);
}
