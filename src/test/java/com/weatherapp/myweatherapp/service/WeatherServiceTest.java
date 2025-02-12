package com.weatherapp.myweatherapp.service;

import com.google.gson.Gson;
import com.weatherapp.myweatherapp.controller.WeatherController;
import com.weatherapp.myweatherapp.model.CityInfo;
import com.weatherapp.myweatherapp.repository.VisualcrossingRepository;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest
@AutoConfigureMockMvc
class WeatherServiceTest {

        @Autowired
        Gson gson;

        @Mock
        VisualcrossingRepository visualcrossingRepository;

        @InjectMocks
        WeatherService weatherService;


        static String london_data= """
            {"address":"london","description":"Similar temperatures continuing with a chance of rain tomorrow & Tuesday.","currentConditions":{"temp":"39.7","sunrise":"07:25:13","sunset":"17:05:05","feelslike":"37.8","humidity":"89.0","conditions":"Partially cloudy"},"days":[{"datetime":"2025-02-09","temp":"39.3","tempmax":"42.7","tempmin":"34.0","conditions":"Rain, Partially cloudy","description":"Partly cloudy throughout the day with rain."},{"datetime":"2025-02-10","temp":"38.2","tempmax":"39.5","tempmin":"36.0","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain or snow."},{"datetime":"2025-02-11","temp":"38.6","tempmax":"41.8","tempmin":"36.0","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain or snow."},{"datetime":"2025-02-12","temp":"38.8","tempmax":"43.1","tempmin":"35.3","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-13","temp":"38.4","tempmax":"43.1","tempmin":"36.0","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-14","temp":"38.1","tempmax":"42.7","tempmin":"35.5","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-15","temp":"38.8","tempmax":"43.4","tempmin":"34.1","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-16","temp":"38.8","tempmax":"41.8","tempmin":"34.1","conditions":"Partially cloudy","description":"Clearing in the afternoon."},{"datetime":"2025-02-17","temp":"35.7","tempmax":"41.4","tempmin":"31.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-18","temp":"36.5","tempmax":"43.1","tempmin":"31.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-19","temp":"36.8","tempmax":"43.8","tempmin":"32.6","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-20","temp":"35.7","tempmax":"40.7","tempmin":"32.3","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-21","temp":"37.1","tempmax":"43.6","tempmin":"31.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-22","temp":"35.0","tempmax":"40.0","tempmin":"31.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-23","temp":"33.9","tempmax":"37.8","tempmin":"31.7","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."}]}
            """;

        static String dublin_data = """
            {"address":"dublin","description":"Similar temperatures continuing with a chance of rain tomorrow & Friday.","currentConditions":{"temp":"39.9","sunrise":"07:52:53","sunset":"17:26:27","feelslike":"34.1","humidity":"88.8","conditions":"Partially cloudy"},"days":[{"datetime":"2025-02-10","temp":"40.9","tempmax":"42.7","tempmin":"39.1","conditions":"Rain, Partially cloudy","description":"Partly cloudy throughout the day with a chance of rain throughout the day."},{"datetime":"2025-02-11","temp":"42.3","tempmax":"43.6","tempmin":"41.1","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with rain or snow clearing later."},{"datetime":"2025-02-12","temp":"42.9","tempmax":"43.8","tempmin":"41.8","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-13","temp":"42.4","tempmax":"43.1","tempmin":"41.7","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-14","temp":"40.9","tempmax":"41.6","tempmin":"39.8","conditions":"Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain."},{"datetime":"2025-02-15","temp":"39.7","tempmax":"42.3","tempmin":"36.9","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-16","temp":"36.6","tempmax":"38.2","tempmin":"34.2","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-17","temp":"34.0","tempmax":"36.4","tempmin":"32.4","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-18","temp":"35.4","tempmax":"39.6","tempmin":"31.9","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-19","temp":"38.1","tempmax":"41.1","tempmin":"34.4","conditions":"Rain, Overcast","description":"Cloudy skies throughout the day with late afternoon rain."},{"datetime":"2025-02-20","temp":"40.2","tempmax":"44.0","tempmin":"38.0","conditions":"Rain, Partially cloudy","description":"Partly cloudy throughout the day with early morning rain."},{"datetime":"2025-02-21","temp":"40.0","tempmax":"42.2","tempmin":"37.8","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-22","temp":"41.6","tempmax":"46.3","tempmin":"36.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-23","temp":"40.8","tempmax":"44.7","tempmin":"38.3","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-24","temp":"39.7","tempmax":"44.7","tempmin":"37.7","conditions":"Rain, Partially cloudy","description":"Partly cloudy throughout the day with a chance of rain."}]}
            """;

        static String paris_data = """
            {"address":"paris","description":"Similar temperatures continuing with no rain expected.","currentConditions":{"temp":"38.6","sunrise":"08:07:02","sunset":"18:03:29","feelslike":"34.3","humidity":"95.5","conditions":"Overcast"},"days":[{"datetime":"2025-02-10","temp":"40.2","tempmax":"42.1","tempmin":"36.7","conditions":"Rain, Overcast","description":"Cloudy skies throughout the day with a chance of rain throughout the day."},{"datetime":"2025-02-11","temp":"39.7","tempmax":"44.7","tempmin":"35.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-12","temp":"40.3","tempmax":"42.7","tempmin":"37.1","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-13","temp":"42.1","tempmax":"44.1","tempmin":"40.9","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-14","temp":"39.9","tempmax":"42.3","tempmin":"36.2","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-15","temp":"35.9","tempmax":"42.7","tempmin":"31.4","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-16","temp":"33.7","tempmax":"39.3","tempmin":"30.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-17","temp":"32.3","tempmax":"39.6","tempmin":"27.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-18","temp":"34.2","tempmax":"42.9","tempmin":"28.8","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-19","temp":"35.6","tempmax":"42.3","tempmin":"30.8","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-20","temp":"43.3","tempmax":"50.3","tempmin":"37.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-21","temp":"44.8","tempmax":"49.0","tempmin":"41.8","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-22","temp":"50.4","tempmax":"53.9","tempmin":"46.8","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-23","temp":"49.8","tempmax":"53.0","tempmin":"45.5","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-24","temp":"46.8","tempmax":"51.3","tempmin":"44.5","conditions":"Overcast","description":"Cloudy skies throughout the day."}]}    
            """;
        static String delhi_data = """
            {"address":"delhi","description":"Warming up with no rain expected.","currentConditions":{"temp":"59.8","sunrise":"07:02:45","sunset":"18:08:27","feelslike":"59.8","humidity":"86.1","conditions":"Rain"},"days":[{"datetime":"2025-02-11","temp":"70.5","tempmax":"83.4","tempmin":"58.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-12","temp":"70.8","tempmax":"82.3","tempmin":"61.2","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-13","temp":"68.6","tempmax":"80.7","tempmin":"58.7","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-14","temp":"70.0","tempmax":"83.4","tempmin":"58.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-15","temp":"73.9","tempmax":"86.6","tempmin":"63.1","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-16","temp":"75.9","tempmax":"88.1","tempmin":"65.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-17","temp":"77.6","tempmax":"89.3","tempmin":"68.8","conditions":"Partially cloudy","description":"Clearing in the afternoon."},{"datetime":"2025-02-18","temp":"76.7","tempmax":"89.1","tempmin":"67.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-19","temp":"74.0","tempmax":"87.2","tempmin":"64.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-20","temp":"70.0","tempmax":"81.2","tempmin":"61.1","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-21","temp":"72.4","tempmax":"84.5","tempmin":"62.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-22","temp":"73.5","tempmax":"84.8","tempmin":"63.6","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-23","temp":"74.4","tempmax":"86.4","tempmin":"64.3","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-24","temp":"74.1","tempmax":"87.0","tempmin":"63.4","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-25","temp":"74.9","tempmax":"88.8","tempmin":"63.1","conditions":"Clear","description":"Clear conditions throughout the day."}]}    
            """;
        static String amsterdam_data= """
                {"address":"amsterdam","description":"Similar temperatures continuing with a chance of rain today.","currentConditions":{"temp":"38.9","sunrise":"08:01:48","sunset":"17:48:16","feelslike":"38.9","humidity":"97.7","conditions":"Rain, Partially cloudy"},"days":[{"datetime":"2025-02-12","temp":"38.3","tempmax":"41.6","tempmin":"36.0","conditions":"Snow, Rain, Overcast","description":"Cloudy skies throughout the day with rain or snow clearing later."},{"datetime":"2025-02-13","temp":"35.6","tempmax":"38.6","tempmin":"33.3","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-14","temp":"35.5","tempmax":"38.0","tempmin":"33.3","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-15","temp":"33.8","tempmax":"37.3","tempmin":"31.5","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-16","temp":"34.9","tempmax":"40.7","tempmin":"31.0","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-17","temp":"36.4","tempmax":"43.4","tempmin":"32.7","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-18","temp":"35.6","tempmax":"42.7","tempmin":"29.7","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-19","temp":"39.3","tempmax":"46.8","tempmin":"33.3","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-20","temp":"47.0","tempmax":"52.8","tempmin":"41.1","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-21","temp":"50.9","tempmax":"56.7","tempmin":"47.0","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-22","temp":"50.1","tempmax":"52.2","tempmin":"48.8","conditions":"Overcast","description":"Cloudy skies throughout the day."},{"datetime":"2025-02-23","temp":"50.9","tempmax":"56.7","tempmin":"46.5","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-24","temp":"51.8","tempmax":"58.4","tempmin":"46.8","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-25","temp":"50.7","tempmax":"56.4","tempmin":"45.0","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-26","temp":"53.0","tempmax":"54.4","tempmin":"51.7","conditions":"Overcast","description":"Cloudy skies throughout the day."}]}
                """;
        static String unknown_data = """
                {"address":"delhi","description":"Warming up with no rain expected.","currentConditions":{"temp":"59.8","sunrise":"07:02:45","sunset":"18:08:X27","feelslike":"59.8","humidity":"86.1","conditions":"0x7F\"},"days":[{"datetime":"2025-02-11","temp":"70.5","tempmax":"83.4","tempmin":"58.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-12","temp":"70.8","tempmax":"82.3","tempmin":"61.2","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-13","temp":"68.6","tempmax":"80.7","tempmin":"58.7","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-14","temp":"70.0","tempmax":"83.4","tempmin":"58.9","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-15","temp":"73.9","tempmax":"86.6","tempmin":"63.1","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-16","temp":"75.9","tempmax":"88.1","tempmin":"65.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-17","temp":"77.6","tempmax":"89.3","tempmin":"68.8","conditions":"Partially cloudy","description":"Clearing in the afternoon."},{"datetime":"2025-02-18","temp":"76.7","tempmax":"89.1","tempmin":"67.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-19","temp":"74.0","tempmax":"87.2","tempmin":"64.9","conditions":"Partially cloudy","description":"Partly cloudy throughout the day."},{"datetime":"2025-02-20","temp":"70.0","tempmax":"81.2","tempmin":"61.1","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-21","temp":"72.4","tempmax":"84.5","tempmin":"62.0","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-22","temp":"73.5","tempmax":"84.8","tempmin":"63.6","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-23","temp":"74.4","tempmax":"86.4","tempmin":"64.3","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-24","temp":"74.1","tempmax":"87.0","tempmin":"63.4","conditions":"Clear","description":"Clear conditions throughout the day."},{"datetime":"2025-02-25","temp":"74.9","tempmax":"88.8","tempmin":"63.1","conditions":"Clear","description":"Clear conditions throughout the day."}]}    
                """;




    // TODO: 12/05/2023 write unit tests
    @Test
    public void testLongestDayLight(){
        // given
        when(weatherService.forecastByCity("london")).thenReturn(gson.fromJson(london_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("dublin")).thenReturn(gson.fromJson(dublin_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("paris")).thenReturn(gson.fromJson(paris_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("delhi")).thenReturn(gson.fromJson(delhi_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("unknown")).thenReturn(gson.fromJson(unknown_data.trim(), CityInfo.class));
        // validate mock is working
        Assertions.assertNotNull(weatherService.forecastByCity("london"));
        // Scenario 1 - when longest duration is for city1
        Assertions.assertEquals("london",weatherService.getLongestDaylight("london","dublin"));
        // Scenario 2 - when longest duration is for city2
        Assertions.assertEquals("paris",weatherService.getLongestDaylight("london","paris"));
        // Scenario 3 - when both city is same
        Assertions.assertEquals("paris",weatherService.getLongestDaylight("paris","paris"));
        // Scenario 4 -  failure in test case
        Assertions.assertEquals("Failed to get the longest daylight city",weatherService.getLongestDaylight("unknown","paris"));
    }
    @Test
    public void testRainingCity(){
        // given
        when(weatherService.forecastByCity("london")).thenReturn(gson.fromJson(london_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("dublin")).thenReturn(gson.fromJson(dublin_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("delhi")).thenReturn(gson.fromJson(delhi_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("amsterdam")).thenReturn(gson.fromJson(amsterdam_data.trim(), CityInfo.class));
        when(weatherService.forecastByCity("unknown")).thenReturn(gson.fromJson(unknown_data.trim(), CityInfo.class));
        // validate mock is working
        Assertions.assertNotNull(weatherService.forecastByCity("london"));
        // Scenario 1 - when it's not raining in both the cities
        Assertions.assertEquals("None",weatherService.getCurrentlyRainingCity("london","dublin"));
        // Scenario 2 - when it's raining in one city
        Assertions.assertEquals("amsterdam",weatherService.getCurrentlyRainingCity("amsterdam","london"));
        // Scenario 3 - when it's raining in  both the cities
        Assertions.assertEquals("amsterdam & delhi",weatherService.getCurrentlyRainingCity("amsterdam","delhi"));
        // Scenario 4 - when both city is same
        Assertions.assertEquals("None",weatherService.getCurrentlyRainingCity("dublin","dublin"));
        Assertions.assertEquals("amsterdam",weatherService.getCurrentlyRainingCity("amsterdam","amsterdam"));
        // Scenario 5 -  failure in test case
        Assertions.assertEquals("Failed to get currently raining city",weatherService.getCurrentlyRainingCity("unknown","paris"));
    }

}