package com.example.coolweather.db;

import org.litepal.crud.LitePalSupport;

public class Province extends LitePalSupport {

    private String provinceName;

    private int provinceCode;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }
}
