package com.example.otus.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {
    private final Logger logger = LogManager.getLogger(LoginPage.class);

    // Локаторы
    private final By LOGIN_BUTTON = By.cssSelector("button.sc-mrx253-0");
    private final By PERSONAL_AREA_ELEMENT = By.cssSelector("div.hGvqzc");
    private final By EMAIL_INPUT = By.cssSelector("input[name='email']");
    private final By PASSWORD_ELEMENT = By.cssSelector(".sc-11ptd2v-1-Component");
    private final By PASSWORD_INPUT = By.cssSelector("input[type='password']");
    private final By LOGIN_SUBMIT_BUTTON = By.cssSelector("button.eQlGvH");
    private final By LOGGED_IN_INDICATOR = By.cssSelector("div.logged-in-indicator"); // Пример локатора для проверки входа

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void clickLoginButton() {
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_BUTTON));
        loginButton.click();
    }

    public void clickPersonalAreaElement() {
        WebElement personalAreaElement = wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_AREA_ELEMENT));
        personalAreaElement.click();
    }

    public void enterEmail(String email) {
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(EMAIL_INPUT));
        emailInput.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_ELEMENT));
        passwordElement.click();

        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(PASSWORD_INPUT));
        passwordInput.sendKeys(password);
    }

    public void clickLoginSubmitButton() {
        WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(LOGIN_SUBMIT_BUTTON));
        loginSubmitButton.click();
    }

    public void login(String email, String password) {
        try {
            clickLoginButton();
            clickPersonalAreaElement();
            enterEmail(email);
            enterPassword(password);
            clickLoginSubmitButton();
        } catch (Exception e) {
            logger.error("Ошибка входа", e);
            throw new RuntimeException("Ошибка входа: " + e.getMessage());
        }
    }

    public boolean isLoggedIn() {
        try {
            WebElement loggedInIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGGED_IN_INDICATOR));
            return loggedInIndicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
