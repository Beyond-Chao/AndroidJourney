package com.example.javaknowledge.vehicle;

public interface IRangeChecker extends IVehicleChecker {
    public double start();
    public double end();

    public CheckResult checkRange(IVehicle v);
}
