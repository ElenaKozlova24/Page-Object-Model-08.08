package com.example.otus.page;

import factory.WebDriverFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private static final Logger logger = LogManager.getLogger(PersonalAccountPage.class);
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private static final By PERSONAL_AREA_ELEMENT = By.cssSelector("div.hGvqzc");
    private static final By USER_INFO_ELEMENT = By.cssSelector(".sc-1youhxc-0.dwrtLP");
    private static final By PERSONAL_ACCOUNT_LINK = By.cssSelector("a[href='https://otus.ru/learning']");
    private static final By PERSONAL_INFO_LINK = By.cssSelector("a.nav__item[title='О себе']");
    private static final By PERSONAL_INFO_PAGE_INDICATOR = By.cssSelector("div.personal-info-page-indicator"); // Пример локатора для проверки страницы

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get("https://otus.ru/");
    }

    public void clickPersonalAreaElement() {
        WebElement personalAreaElement = wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_AREA_ELEMENT));
        personalAreaElement.click();
    }

    public void clickUserInfoElement() {
        WebElement userInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(USER_INFO_ELEMENT));
        userInfo.click();
    }

    public void clickPersonalAccountLink() {
        WebElement personalAccount = wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_ACCOUNT_LINK));
        personalAccount.click();
    }

    public void clickPersonalInfoLink() {
        WebElement personalInfoLink = wait.until(ExpectedConditions.elementToBeClickable(PERSONAL_INFO_LINK));
        personalInfoLink.click();
    }

    public void goToPersonalInfo() {
        try {
            clickPersonalAreaElement();
            clickUserInfoElement();
            clickPersonalAccountLink();
            clickPersonalInfoLink();
        } catch (Exception e) {
            logger.error("Error navigating to personal info", e);
            throw new RuntimeException("Error navigating to personal info: " + e.getMessage());
        }
    }

    public boolean isOnPersonalInfoPage() {
        try {
            WebElement personalInfoPageIndicator = wait.until(ExpectedConditions.visibilityOfElementLocated(PERSONAL_INFO_PAGE_INDICATOR));
            return personalInfoPageIndicator.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
