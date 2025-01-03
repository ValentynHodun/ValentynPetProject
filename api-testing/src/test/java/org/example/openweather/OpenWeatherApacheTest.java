package org.example.openweather;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.example.base.BaseTest;
import org.example.service.WeatherService;
import org.example.testdata.WeatherDataGenerator;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Configuration
@PropertySource({"classpath:environment/${target}/application.properties"})
public class OpenWeatherApacheTest extends BaseTest {
    private String API_KEY;
    private String BASE_URL;

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        API_KEY = getApiKey();
        BASE_URL = getBaseUrl() + "onecall";
    }

    @SneakyThrows
    @Test(groups = "smoke")
    public void testGetWeatherData() {
        WeatherDataGenerator weatherDataGenerator = new WeatherDataGenerator(BASE_URL, API_KEY);
        WeatherService weatherService = new WeatherService();
        String url = weatherService.buildUrl(weatherDataGenerator);

        HttpGet httpGet = weatherService.buildHttpRequest(url);
        CloseableHttpResponse closeableHttpResponse = weatherService.executeHttpRequest(httpGet);
        assertThat(closeableHttpResponse.getStatusLine().getStatusCode()).isEqualTo(200);

        HttpEntity httpEntity = weatherService.extractEntityFromResponse(closeableHttpResponse);
        String responseString = weatherService.getResponseToString(httpEntity);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(responseString);

        JsonNode currentNode = rootNode.path("current");
        assertThat(currentNode).isNotNull();
        assertThat(currentNode.path("temp").asDouble()).isGreaterThan(-100);
        //todo -> or create model WeatherResponse weatherResponse;
    }

}
