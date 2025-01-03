package org.example.testdata;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherDataGenerator {

    private double latitude = 49.8397;
    private double longitude = 24.0297;
    private String exclude = "minutely,daily,alerts";
    private String appId;
    private String baseUrl;

    public WeatherDataGenerator(String baseUrl, String appId) {
        this.baseUrl = baseUrl;
        this.appId = appId;
    }

}
