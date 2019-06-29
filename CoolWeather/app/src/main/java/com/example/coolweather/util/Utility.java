package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.Country;
import com.example.coolweather.db.Province;
import com.example.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Utility {

    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvinces = new JSONArray(response);
                AddressManager.getInstance().provinceList.clear();

                for (int i = 0; i < allProvinces.length(); i++) {
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));

//                    province.save();
                    AddressManager.getInstance().provinceList.add(province);
                }

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response);

                List<City> tempCities = new ArrayList<>();

                for (int i = 0; i < allCities.length(); i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);

//                    city.save();
                    tempCities.add(city);
                }
                AddressManager.getInstance().provinceToCities.put(provinceId, tempCities);

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    public static boolean handleCountryResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountries = new JSONArray(response);

                List<Country> tempCountries = new ArrayList<>();

                for (int i = 0; i < allCountries.length(); i++) {
                    JSONObject cityObject = allCountries.getJSONObject(i);

                    Country country = new Country();
                    country.setCountryName(cityObject.getString("name"));
                    country.setWeatherId(cityObject.getString("weather_id"));
                    country.setCityId(cityId);

//                    country.save();
                    tempCountries.add(country);
                }

                AddressManager.getInstance().cityToCountries.put(cityId, tempCountries);

                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();

            return new Gson().fromJson(weatherContent, Weather.class);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
