package org.example.openweather;

import org.example.base.BaseTest;
import org.example.pages.MainPage;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;

public class OffersTest extends BaseTest {

    @Test(groups = "smoke")
    public void userCanSearchCorrectCountryWeather() {
        open(baseUrl);
        String cityToFind = "Kraków";
        MainPage mainPage = new MainPage();
        mainPage.setCountry(cityToFind)
                .clickSearchButton()
                .clickOnTheResultInDropDown();

        String defaultCity = mainPage.getCityNameResult();

        assertThat(mainPage.getCityNameFromResult(defaultCity)).contains(cityToFind);
    }
}
