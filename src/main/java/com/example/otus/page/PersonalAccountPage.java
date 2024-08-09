package com.example.otus.page;

import factory.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Локаторы
    private static final By PERSONAL_AREA_ELEMENT = By.cssSelector("div.hGvqzc");
    private static final By USER_INFO_ELEMENT = By.cssSelector(".sc-1youhxc-0.dwrtLP");
    private static final By PERSONAL_ACCOUNT_LINK = By.cssSelector("a[href='https://otus.ru/learning']");
    private static final By PERSONAL_INFO_LINK = By.cssSelector("a.nav__item[title='О себе']");

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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        WebDriver driver = WebDriverFactory.createNewDriver("chrome");

        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);

        personalAccountPage.open();
        personalAccountPage.goToPersonalInfo();

        driver.quit();
    }
}
