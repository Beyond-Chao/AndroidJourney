package com.example.javaknowledge.vehicle;

import java.util.Date;

public class Truck implements IVehicle, IIdentifiable {
    private final double oil;
    private final double temperature;

    private final String pinCode;
    private final String manufacturer;
    private final String licensePlate;
    private final Date produceDate;

    public double getOil() {
        return oil;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String getPINCode() {
        return pinCode;
    }

    @Override
    public String identifier() {
        return licensePlate;
    }

    public String getPinCode() {
        return pinCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getLicenseTag() {
        return licensePlate;
    }

    public Date getProduceDate() {
        return produceDate;
    }

    public Truck(double oil, double temperature, String pinCode, String manufacturer, String licensePlate, Date produceDate) {
        this.oil = oil;
        this.temperature = temperature;

        this.pinCode = pinCode;
        this.manufacturer = manufacturer;
        this.licensePlate = licensePlate;
        this.produceDate = produceDate;
    }
}
