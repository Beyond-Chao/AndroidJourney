package com.example.coolweather.util;

import com.example.coolweather.db.City;
import com.example.coolweather.db.Country;
import com.example.coolweather.db.Province;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AddressManager {

    public List<Province> provinceList = new ArrayList<>();
    public Map<Integer, List<City>> provinceToCities = new HashMap<Integer, List<City>>();
    public Map<Integer, List<Country>> cityToCountries = new HashMap<Integer, List<Country>>();
//    public List<Country> countryList = new ArrayList<>();

    private static AddressManager instance = new AddressManager();

    public static AddressManager getInstance() {
        return instance;
    }

}
