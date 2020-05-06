package com.poweremabox.homeworkweek5.service;

import com.poweremabox.homeworkweek5.model.WeahterFacts;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(prefix = "wc")
public class WeatherService {

    private final RestTemplate restTemplate;
    private String key;
    private String city;

    public WeatherService() {
        this.restTemplate = new RestTemplate();
    }

    private WeahterFacts connectWithWeatherApi() {
        String link = "https://api.openweathermap.org/data/2.5/weather?q=" +
                city +
                "&appid=" +
                key;
        return restTemplate.getForObject(link, WeahterFacts.class);
    }

    public String getWeatherPicture() {
        String icon = connectWithWeatherApi().getWeather().get(0).getIcon();
        return " http://openweathermap.org/img/w/"+icon+".png";

    }
    public String getTempInfo(){
        return String.format("%.2f", (connectWithWeatherApi().getMain().getTemp()) - 273.15);
    }
    public String getCloudInfo(){
        return connectWithWeatherApi().getWeather().get(0).getDescription();
    }
    public String getPressureInfo(){
        return String.valueOf(connectWithWeatherApi().getMain().getPressure());
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
