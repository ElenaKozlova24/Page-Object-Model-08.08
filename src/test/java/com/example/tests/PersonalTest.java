package com.example.tests;

import com.example.otus.page.LoginPage;
import com.example.otus.page.PersonalAccountPage;
import com.example.otus.page.PersonalInfoPage;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

public class PersonalTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createNewDriver("chrome");
    }

    @ParameterizedTest
    @MethodSource("provideCredentials")
    public void testPersonalInfo(String email, String password) {
        try {
            performLogin(email, password);
            navigateToPersonalInfo();
            fillPersonalInfo();
            performLogin(email, password);
            navigateToPersonalInfo();
            verifyPersonalInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void performLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(email, password);
    }

    private void navigateToPersonalInfo() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.goToPersonalInfo();
    }

    private void fillPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        personalInfoPage.fillPersonalInfo();
    }

    private void verifyPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        personalInfoPage.verifyPersonalInfo();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
         driver.quit();
        }
    }

    private static Stream<Arguments> provideCredentials() {
        return Stream.of(
                Arguments.of("4688104@mail.ru", "Kozlova23.")
        );
    }
}
