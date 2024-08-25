package com.example.tests;

import com.example.otus.page.LoginPage;
import com.example.otus.page.PersonalAccountPage;
import com.example.otus.page.PersonalInfoPage;
import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.stream.Stream;
import org.junit.jupiter.params.provider.Arguments;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonalTest {

    private static final Logger logger = LogManager.getLogger(PersonalTest.class);
    private WebDriver driver;
    private Properties properties;

    @BeforeEach
    public void setUp() {
        driver = WebDriverFactory.createNewDriver("chrome");
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Не удалось найти config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            logger.error("Ошибка при загрузке файла свойств", ex);
        }
    }

    @ParameterizedTest
    @MethodSource("provideCredentials")
    public void testPersonalInfo(String email, String password) {
        performLogin(email, password);
        navigateToPersonalInfo();
        fillPersonalInfo();
        clearCookies();
        performLogin(email, password);
        navigateToPersonalInfo();
        verifyPersonalInfo();
    }

    private void performLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(email, password);
        assertTrue(loginPage.isLoggedIn(), "Ошибка входа");
    }

    private void navigateToPersonalInfo() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.goToPersonalInfo();
        assertTrue(personalAccountPage.isOnPersonalInfoPage(), "Ошибка при переходе к странице личной информации");
    }

    private void fillPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        personalInfoPage.fillPersonalInfo();
        assertTrue(personalInfoPage.isPersonalInfoFilled(), "Ошибка при заполнении личной информации");
    }

    private void verifyPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        assertTrue(personalInfoPage.isPersonalInfoCorrect(), "Ошибка при проверке личной информации");
    }

    private void clearCookies() {
        driver.manage().deleteAllCookies();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static Stream<Arguments> provideCredentials() {
        String email = System.getenv("EMAIL");
        String password = System.getenv("PASSWORD");
        if (email == null || password == null) {
            logger.error("Переменные окружения EMAIL и PASSWORD не установлены");
            return Stream.empty();
        }
        return Stream.of(
                Arguments.of(email, password)
        );
    }
}
