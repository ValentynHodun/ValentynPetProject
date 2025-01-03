package org.example.pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {

    @Step
    public MainPage setCountry(String country) {
        $(byAttribute("placeholder", "Search city")).shouldBe(Condition.visible).setValue(country);
        return this;
    }

    @Step
    public MainPage clickSearchButton() {
        $(byText("Search")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step
    public MainPage clickOnTheResultInDropDown() {
        $(byXpath("//ul[@class='search-dropdown-menu']/li")).shouldBe(Condition.visible).click();
        return this;
    }

    @Step
    public String getCityNameResult() {
        return $(byXpath("//div[@class = 'current-container mobile-padding']/div[1]/h2")).shouldBe(Condition.visible).getText();
    }

    @Step
    public String getCityNameFromResult(String defaultCityThatShouldBeChanged) {
        return $(byXpath("//div[@class = 'current-container mobile-padding']/div[1]/h2")).shouldNotHave(Condition.text(defaultCityThatShouldBeChanged)).getText();
    }
}
