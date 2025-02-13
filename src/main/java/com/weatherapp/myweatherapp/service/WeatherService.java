package com.weatherapp.myweatherapp.service;

import com.google.gson.Gson;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.json.JSONException;
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

  public String getCurrentlyRainingCity(String city1,String city2){

    try {
      CityInfo ci1 = forecastByCity(city1);
      String rainConditionCity1 = getDataFromJson(ci1, "conditions");
      boolean isRainingCity1 = rainConditionCity1.toLowerCase().contains("rain");
      //If both the cities are same
      if (city1.equals(city2)) {

        if (isRainingCity1) {
          System.out.println("It is currently raining in " + city1);
          return city1;

        }
        System.out.println("It is not currently raining in " + city1);
        return "None";
      } else {

        CityInfo ci2 = forecastByCity(city2);
        // Getting rainfall info for two cities
        String rainConditionCity2 = getDataFromJson(ci2, "conditions");

        boolean isRainingCity2 = rainConditionCity2.toLowerCase().contains("rain");

        if (isRainingCity1 && isRainingCity2) {
          System.out.println("It is currently raining in both " + city1 + " and " + city2 + ".");
          return String.format("%s & %s", city1, city2);
        } else if (isRainingCity1) {
          System.out.println("It is currently raining in " + city1 + ".");
          return city1;
        } else if (isRainingCity2) {
          System.out.println("It is currently raining in " + city2 + ".");
          return city2;
        } else {
          System.out.println("It is not currently raining in either " + city1 + " or " + city2 + ".");
          return "None";
        }
      }
    } catch (JSONException e) {
      System.out.println(e.getMessage());
    }
    return "Failed to get currently raining city";
  }
  //method to extract from json from the current conditions

  private String getDataFromJson(CityInfo ci1,String datastring) {
    Gson gson = new Gson();
    JSONObject jsonob1 = new JSONObject(gson.toJson(ci1));
    JSONObject jsonob2 = jsonob1.getJSONObject("currentConditions");
    return jsonob2.getString(datastring);
  }

  }


