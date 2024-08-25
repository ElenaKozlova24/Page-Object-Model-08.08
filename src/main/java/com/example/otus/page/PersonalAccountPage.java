package com.example.otus.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PersonalAccountPage extends BasePage {
    private final Logger logger = LogManager.getLogger(PersonalAccountPage.class);

    // Локаторы
    private final By PERSONAL_AREA_ELEMENT = By.cssSelector("div.hGvqzc");
    private final By USER_INFO_ELEMENT = By.cssSelector(".sc-1youhxc-0.dwrtLP");
    private final By PERSONAL_ACCOUNT_LINK = By.cssSelector("a[href='https://otus.ru/learning']");
    private final By PERSONAL_INFO_LINK = By.cssSelector("a.nav__item[title='О себе']");
    private final By PERSONAL_INFO_PAGE_INDICATOR = By.cssSelector("div.personal-info-page-indicator"); // Пример локатора для проверки страницы

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
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
            logger.error("Ошибка при переходе к личной информации", e);
            throw new RuntimeException("Ошибка при переходе к личной информации: " + e.getMessage());
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
