package com.weatherapp.myweatherapp.service;

import com.google.gson.Gson;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WeatherService {

  @Autowired
  VisualcrossingRepository weatherRepo;

  @Autowired
  Gson gson;

  public CityInfo forecastByCity(String city) {

    return weatherRepo.getByCity(city);
  }
  public String getLongestDaylight(String city1,String city2){

    CityInfo ci1 = forecastByCity(city1);
    CityInfo ci2 = forecastByCity(city2);
    // Calculating daylight for city1

    // Getting sunrise time and sunset time for two cities
    String sunriseCity1=getDataFromJson(ci1,"sunrise");
    String sunsetCity1=getDataFromJson(ci1,"sunset");
    String sunriseCity2=getDataFromJson(ci2,"sunrise");
    String sunsetCity2=getDataFromJson(ci2,"sunset");

    //Converting time in string format to Date format
    SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    try {
      Date sunriseTimeCity1 = dateFormat.parse(sunriseCity1);
      Date sunsetTimeCity1 = dateFormat.parse(sunsetCity1);
      Date sunriseTimeCity2 = dateFormat.parse(sunriseCity2);
      Date sunsetTimeCity2 = dateFormat.parse(sunsetCity2);

      //calculating the daylight for the two cities
      long differenceInMillis1 = sunsetTimeCity1.getTime() - sunriseTimeCity1.getTime();
      long differenceInMillis2 = sunsetTimeCity2.getTime() - sunriseTimeCity2.getTime();

      if (differenceInMillis1>differenceInMillis2){
        return city1;
      }
      else{
        return city2;
      }
    } catch (ParseException e) {
      System.out.println(e.getMessage());
    }

    //return ResponseEntity.ok(ci);
    return "Failed to get the longest daylight city";
  }
  //method to extract from json from the current conditions

  private String getDataFromJson(CityInfo ci1,String datastring) {
    JSONObject jsonob1 = new JSONObject(gson.toJson(ci1));
    JSONObject jsonob2 = jsonob1.getJSONObject("currentConditions");
    return jsonob2.getString(datastring);
  }
  }


