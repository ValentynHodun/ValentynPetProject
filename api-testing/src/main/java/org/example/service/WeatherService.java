package org.example.service;

import io.qameta.allure.Step;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.example.testdata.WeatherDataGenerator;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Log4j
@Component
@Scope(value = "thread", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class WeatherService {

    @Step
    public String buildUrl(WeatherDataGenerator params) {
        return String.format("%s?lat=%f&lon=%f&exclude=%s&appid=%s",
                params.getBaseUrl(),
                params.getLatitude(),
                params.getLongitude(),
                params.getExclude(),
                params.getAppId());
    }

    @Step("Building HTTP request for URL")
    public HttpGet buildHttpRequest(String url) {
        return new HttpGet(url);
    }

    @Step("Executing HTTP request: {request}")
    public CloseableHttpResponse executeHttpRequest(HttpGet request) throws Exception {
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)
                .setSocketTimeout(10000)
                .build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(1, TimeUnit.MINUTES);
        connectionManager.setMaxTotal(100);
        connectionManager.setDefaultMaxPerRoute(10);

        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(requestConfig)
                .setConnectionManager(connectionManager)
                .build();

        return httpClient.execute(request);
    }

    @Step()
    public HttpEntity extractEntityFromResponse(CloseableHttpResponse response) {
        return response.getEntity();
    }

    @SneakyThrows
    @Step
    public String getResponseToString(HttpEntity entity) {
        String result = EntityUtils.toString(entity);
        log.info("response result: " + result);
        return result;
    }
}
