package org.example.openweather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static org.assertj.core.api.Assertions.assertThat;

public class OpenWeatherRestAssuredTests extends BaseTest {

    private String API_KEY;
    private String BASE_URL;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        API_KEY = getApiKey();
        BASE_URL = getBaseUrl() + "onecall";
        RestAssured.baseURI = BASE_URL;
    }

    @SneakyThrows
    @Test
    public void testGetWeatherData() {

        Response response = RestAssured.given()
                .param("lat", 49.8397)
                .param("lon", 24.0297)
                .param("exclude", "minutely,daily,alerts")
                .param("appid", API_KEY)
                .when()
                .get();

        assertThat(response.getStatusCode()).isEqualTo(200);

        String responseBody = response.getBody().asString();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseBody);

        JsonNode currentNode = rootNode.path("current");
        assertThat(currentNode).isNotNull();
        assertThat(currentNode.path("temp").asDouble()).isGreaterThan(-100);

        //todo -> or create model WeatherResponse weatherResponse;
    }
}
