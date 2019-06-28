package com.example.coolweather.util;

import com.example.coolweather.db.City;
import com.example.coolweather.db.Country;
import com.example.coolweather.db.Province;

import java.util.ArrayList;
import java.util.List;

public class AddressManager {

    public List<Province> provinceList = new ArrayList<>();
    public List<City> cityList = new ArrayList<>();
    public List<Country> countryList = new ArrayList<>();

    private static AddressManager instance = new AddressManager();

    public static AddressManager getInstance() {
        return instance;
    }

}
