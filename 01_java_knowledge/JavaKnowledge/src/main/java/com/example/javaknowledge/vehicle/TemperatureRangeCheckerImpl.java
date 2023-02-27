package com.example.javaknowledge.vehicle;

public class TemperatureRangeCheckerImpl implements IRangeChecker {

    private double lowTemp;
    private double heightTemp;

    private double underCheckTemp;

    public TemperatureRangeCheckerImpl(double lowTemp, double heightTemp) {
        this.lowTemp = lowTemp;
        this.heightTemp = heightTemp;
    }

    @Override
    public double start() {
        return lowTemp;
    }

    @Override
    public double end() {
        return heightTemp;
    }

    @Override
    public CheckResult checkRange(IVehicle v) {
        underCheckTemp = v.getTemperature();

        boolean isValid = underCheckTemp >= lowTemp && underCheckTemp <= heightTemp;
        return new CheckResult(isValid, isValid ? "" : message());
    }

    @Override
    public String name() {
        return "车内温度检测";
    }

    @Override
    public String message() {
        return String.format("车内温度检测不合格：当前温度 %f 不在 %f-%f 范围内", underCheckTemp, lowTemp, heightTemp);

    }
}
