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
                logger.error("Unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            logger.error("Error loading properties file", ex);
        }
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
            logger.error("Error during test execution", e);
        }
    }

    private void performLogin(String email, String password) {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.open();
        loginPage.login(email, password);
        assertTrue(loginPage.isLoggedIn(), "Login failed");
    }

    private void navigateToPersonalInfo() {
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        personalAccountPage.goToPersonalInfo();
        assertTrue(personalAccountPage.isOnPersonalInfoPage(), "Navigation to personal info page failed");
    }

    private void fillPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        personalInfoPage.fillPersonalInfo();
        assertTrue(personalInfoPage.isPersonalInfoFilled(), "Filling personal info failed");
    }

    private void verifyPersonalInfo() {
        PersonalInfoPage personalInfoPage = new PersonalInfoPage(driver);
        assertTrue(personalInfoPage.isPersonalInfoCorrect(), "Personal info verification failed");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private static Stream<Arguments> provideCredentials() {
        Properties properties = new Properties();
        try (InputStream input = PersonalTest.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                logger.error("Unable to find config.properties");
                return Stream.empty();
            }
            properties.load(input);
        } catch (IOException ex) {
            logger.error("Error loading properties file", ex);
            return Stream.empty();
        }
        String email = properties.getProperty("email");
        String password = properties.getProperty("password");
        return Stream.of(
                Arguments.of(email, password)
        );
    }
}
