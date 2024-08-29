package com.example.otus.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.junit.jupiter.api.Assertions;

public class PersonalInfoPage extends BasePage {
    private final Logger logger = LogManager.getLogger(PersonalInfoPage.class);

    public PersonalInfoPage(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalInfo() {
        try {
            fillBasicInfo();
            selectCountryAndCity();
            selectEnglishLevel();
            selectMovingAndFlexible();
            addTelegramContact();
            addVKContact();
            selectGender();
            fillWorkInfo();
            uploadPhoto();
            saveAndContinue();
        } catch (Exception e) {
            logger.error("Error filling personal info", e);
            throw new RuntimeException("Error filling personal info: " + e.getMessage());
        }
    }

    private void fillBasicInfo() {
        fillInput("input[name='fname']", "Елена");
        fillInput("input[name='fname_latin']", "Elena");
        fillInput("input[name='lname']", "Козлова");
        fillInput("input[name='lname_latin']", "Kozlova");
        fillInput("input[name='blog_name']", "Елена");
        fillInput("input[name='date_of_birth']", "06.10.1984");
    }

    private void selectCountryAndCity() {
        WebElement countryInput = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.js-custom-select-presentation")));
        countryInput.click();
        WebElement russiaOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Россия']")));
        russiaOption.click();

        WebElement citySelect = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > div.body-wrapper > div > div.js-lk-cv > div.container.container-padding-bottom > div.container__row > div.container__col.container__col_9.container__col_md-8.container__col_sm-12.container__col_border-left.lk-rightbar.print-block.print-wide > div > form > div:nth-child(2) > div.container__col.container__col_12 > div:nth-child(1) > div > div.container__col.container__col_9.container__col_ssm-12 > div:nth-child(2) > div.container__col.container__col_9.container__col_md-8.container__col_middle")));
        citySelect.click();
        WebElement yaltaOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".js-custom-select-option[data-value='467']")));
        yaltaOption.click();
    }

    private void selectEnglishLevel() {
        WebElement englishInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(text(), 'Элементарный уровень (Elementary)')]")));
        englishInput.click();
        WebElement elementaryOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Элементарный уровень (Elementary)']")));
        elementaryOption.click();
    }

    private void selectMovingAndFlexible() {
        WebElement moving = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='radio__label' and text()='Да']")));
        moving.click();

        Actions actions2 = new Actions(driver);
        actions2.sendKeys(Keys.PAGE_DOWN).perform();

        WebElement flexible = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='flexible' and @title='Гибкий график']")));
        flexible.click();
    }

    private void addTelegramContact() {
        String addContactLocatorTemplate = "(//button[contains(text(), '%s')]) [last()]";
        WebElement addContactElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(addContactLocatorTemplate, "Добавить"))));
        addContactElement.click();

        // Локатор для выбора метода контакта
        By setMethodLocator = By.xpath("(//span[@class='placeholder']) [last()]");
        WebElement setMethodElement = wait.until(ExpectedConditions.presenceOfElementLocated(setMethodLocator));
        setMethodElement.click();

        // Локатор для выбора Telegram
        String socialNetLocatorTemplate = "(//button[@data-value='%s']) [last()]";
        WebElement telegramElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(socialNetLocatorTemplate, "telegram"))));
        telegramElement.click();

        String fieldSelectorTemplate = "input#id_contact-%d-value";
        WebElement telegramInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format(fieldSelectorTemplate, 0))));
        telegramInputElement.click();
        telegramInputElement.sendKeys("@ElenaK2326");
    }

    private void addVKContact() {
        String addContactLocatorTemplate = "(//button[contains(text(), '%s')]) [last()]";
        WebElement addContactElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(addContactLocatorTemplate, "Добавить"))));
        addContactElement.click();

        // Локатор для выбора метода контакта
        By setMethodLocator = By.xpath("(//span[@class='placeholder']) [last()]");
        WebElement setMethodElement = wait.until(ExpectedConditions.presenceOfElementLocated(setMethodLocator));
        setMethodElement.click();

        // Локатор для выбора VK
        String socialNetLocatorTemplate = "(//button[@data-value='%s']) [last()]";
        WebElement vkElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(String.format(socialNetLocatorTemplate, "vk"))));
        vkElement.click();

        // Локатор для поля ввода контакта
        String fieldSelectorTemplate = "input#id_contact-%d-value";
        WebElement vkInputElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(String.format(fieldSelectorTemplate, 1))));
        vkInputElement.click();
        vkInputElement.sendKeys("id10022572");
    }

    private void selectGender() {
        WebElement genderElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.name("gender")));
        Select select = new Select(genderElement);
        select.selectByValue("f");
    }

    private void fillWorkInfo() {
        fillInput("input[name='id_company']", "КСП Московской области");
        fillInput("input[name='id_work']", "Главный инспектор");

        WebElement experience = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_experience-0-experience")));
        Select experienceSelect = new Select(experience);
        experienceSelect.selectByVisibleText("Java");

        WebElement level = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("id_experience-0-level")));
        Select levelSelect = new Select(level);
        levelSelect.selectByValue("1");
    }

    private void uploadPhoto() {
        WebElement fileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input.file-in-button.js-avatar-file")));
        fileInput.sendKeys("path/to/your/desktop/foto");

        WebElement chooseButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.button.button_blue.js-choose-crop")));
        chooseButton.click();
    }

    private void saveAndContinue() {
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[title='Сохранить и продолжить']")));
        saveButton.click();
    }

    private void fillInput(String cssSelector, String value) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        input.clear();
        input.sendKeys(value);
    }

    public void verifyPersonalInfo() {
        Assertions.assertEquals("Елена", getInputValue("input[name='fname']"));
        Assertions.assertEquals("Elena", getInputValue("input[name='fname_latin']"));
        Assertions.assertEquals("Козлова", getInputValue("input[name='lname']"));
        Assertions.assertEquals("Kozlova", getInputValue("input[name='lname_latin']"));
        Assertions.assertEquals("Елена", getInputValue("input[name='blog_name']"));
        Assertions.assertEquals("06.10.1984", getInputValue("input[name='date_of_birth']"));
        Assertions.assertEquals("@ElenaK2326", getInputValue("input#id_contact-0-value"));
        Assertions.assertEquals("id10022572", getInputValue("input#id_contact-1-value"));
        Assertions.assertEquals("КСП Московской области", getInputValue("input[name='id_company']"));
        Assertions.assertEquals("Главный инспектор", getInputValue("input[name='id_work']"));
    }

    private String getInputValue(String cssSelector) {
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(cssSelector)));
        return input.getAttribute("value");
    }

    public boolean isPersonalInfoFilled() {
        try {
            // Проверка заполненности основных полей
            Assertions.assertNotNull(getInputValue("input[name='fname']"));
            Assertions.assertNotNull(getInputValue("input[name='fname_latin']"));
            Assertions.assertNotNull(getInputValue("input[name='lname']"));
            Assertions.assertNotNull(getInputValue("input[name='lname_latin']"));
            Assertions.assertNotNull(getInputValue("input[name='blog_name']"));
            Assertions.assertNotNull(getInputValue("input[name='date_of_birth']"));
            Assertions.assertNotNull(getInputValue("input#id_contact-0-value"));
            Assertions.assertNotNull(getInputValue("input#id_contact-1-value"));
            Assertions.assertNotNull(getInputValue("input[name='id_company']"));
            Assertions.assertNotNull(getInputValue("input[name='id_work']"));

            // Проверка заполненности других полей, если необходимо
            // ...

            return true;
        } catch (Exception e) {
            logger.error("Personal info is not fully filled", e);
            return false;
        }
    }

    public boolean isPersonalInfoCorrect() {
        try {
            // Проверка правильности запоииииилнения основных полей
            Assertions.assertEquals("Елена", getInputValue("input[name='fname']"));
            Assertions.assertEquals("Elena", getInputValue("input[name='fname_latin']"));
            Assertions.assertEquals("Козлова", getInputValue("input[name='lname']"));
            Assertions.assertEquals("Kozlova", getInputValue("input[name='lname_latin']"));
            Assertions.assertEquals("Елена", getInputValue("input[name='blog_name']"));
            Assertions.assertEquals("06.10.1984", getInputValue("input[name='date_of_birth']"));
            Assertions.assertEquals("@ElenaK2326", getInputValue("input#id_contact-0-value"));
            Assertions.assertEquals("id10022572", getInputValue("input#id_contact-1-value"));
            Assertions.assertEquals("КСП Московской области", getInputValue("input[name='id_company']"));
            Assertions.assertEquals("Главный инспектор", getInputValue("input[name='id_work']"));

            // Проверка правильности заполнения других полей, если необходимо
            // ...

            return true;
        } catch (Exception e) {
            logger.error("Personal info is not correct", e);
            return false;
        }
    }
}
