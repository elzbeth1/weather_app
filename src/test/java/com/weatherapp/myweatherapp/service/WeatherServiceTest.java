package com.weatherapp.myweatherapp.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherServiceTest {

    static String data= """
            {"address":"london","description":"Similar temperatures continuing with a chance of rain tomorrow & Tuesday.","currentConditions":{"temp":"39.7","sunrise":"07:25:13","sunset":"17:05:05","feelslike":"37.8","humidity":"89.0","conditions":"Partially cloudy"},"days":[{"datetime":"2025-02-09","temp":"39.3","tempmax":"42.7","tempmin":"34.0","conditions":"Rain, Partially cloudy","description":"Partly cloudy throughout the day with rain."},{"datetime":"2025-02-10","temp":"38.2","tempmax":"39.5","tempmin":"36.0","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain or snow."},{"datetime":"2025-02-11","temp":"38.6","tempmax":"41.8","tempmin":"36.0","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain or snow."},{"datetime":"2025-02-12","temp":"38.8","tempmax":"43.1","tempmin":"35.3","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-13","temp":"38.4","tempmax":"43.1","tempmin":"36.0","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-14","temp":"38.1","tempmax":"42.7","tempmin":"35.5","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-15","temp":"38.8","tempmax":"43.4","tempmin":"34.1","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-16","temp":"38.8","tempmax":"41.8","tempmin":"34.1","conditions":"Partially cloudy","description":"Clearing in the afternoon."},{"datetime":"2025-02-17","temp":"35.7","tempmax":"41.4","tempmin":"31.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-18","temp":"36.5","tempmax":"43.1","tempmin":"31.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-19","temp":"36.8","tempmax":"43.8","tempmin":"32.6","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-20","temp":"35.7","tempmax":"40.7","tempmin":"32.3","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-21","temp":"37.1","tempmax":"43.6","tempmin":"31.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-22","temp":"35.0","tempmax":"40.0","tempmin":"31.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-23","temp":"33.9","tempmax":"37.8","tempmin":"31.7","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."}]}
            """;
  // TODO: 12/05/2023 write unit tests
    @Test
    public void testLongestDayLight(){
       // TODO pending to complete
    }
}