package com.example.coolweather.db;

import org.litepal.crud.LitePalSupport;

public class City extends LitePalSupport {

    private String cityName;

    private int cityCode;

    private int provinceId;


    public String getCityName() {
        return cityName;
    }

    public int getCityCode() {
        return cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }
}
