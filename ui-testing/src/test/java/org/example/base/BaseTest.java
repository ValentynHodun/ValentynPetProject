package org.example.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import org.example.UITestConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.example.config.ApiBaseTestConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

@ContextConfiguration(classes = {UITestConfig.class})
public class BaseTest extends AbstractTestNGSpringContextTests {
    @Autowired
    Environment env;

    @BeforeClass(alwaysRun = true)
    public void registerSelenideListener() {
        SelenideLogger.addListener("allureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    protected <T> T at(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected <T, V> T at(Class<T> decoratorClass, Class<V> decoratorTargetClass) {
        try {
            return decoratorClass.getDeclaredConstructor(decoratorTargetClass.getInterfaces()).newInstance(decoratorTargetClass.getDeclaredConstructor().newInstance());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected void logout() {
        Selenide.clearBrowserLocalStorage();
    }

}
