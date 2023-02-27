package com.example.javaknowledge.vehicle;

public class OilRangeCheckerImpl implements IRangeChecker {

    private double startOil;
    private double endOil;

    private double underCheckOil;

    public OilRangeCheckerImpl(double startOil, double endOil) {
        this.startOil = startOil;
        this.endOil = endOil;
    }

    @Override
    public double start() {
        return startOil;
    }

    @Override
    public double end() {
        return endOil;
    }

    @Override
    public CheckResult checkRange(IVehicle v) {
        underCheckOil = v.getOil();
        boolean isValid = underCheckOil > start() && underCheckOil < end();

        return new CheckResult(isValid, isValid ? "" : message());
    }

    @Override
    public String name() {
        return "油量检查";
    }

    @Override
    public String message() {
        return String.format("油量检查不合格：当前油量 %s 不在 %s-%s 范围内", underCheckOil, startOil, endOil);
    }
}
