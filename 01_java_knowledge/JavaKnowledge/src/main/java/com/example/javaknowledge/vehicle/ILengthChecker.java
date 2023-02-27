package com.example.javaknowledge.vehicle;

public interface ILengthChecker extends IVehicleChecker {
    int validLength();

    CheckResult checkLength(IVehicle v);
}
