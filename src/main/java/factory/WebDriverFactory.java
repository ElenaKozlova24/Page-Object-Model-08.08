package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private static final String DEFAULT_CHROME_DRIVER_VERSION = "114.0.5735.90"; // Пример версии ChromeDriver

    static {
        // Задаем значение по умолчанию для системного свойства
        if (System.getProperty("chrome.driver.version") == null) {
            System.setProperty("chrome.driver.version", DEFAULT_CHROME_DRIVER_VERSION);
        }
    }

    public static WebDriver createNewDriver(String webDriverName, Object... options) {
        // Получаем версию драйвера из системного свойства
        String chromeDriverVersion = System.getProperty("chrome.driver.version");

        switch (webDriverName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().driverVersion(chromeDriverVersion).setup();
                if (options.length > 0 && options[0] instanceof ChromeOptions) {
                    return new ChromeDriver((ChromeOptions) options[0]);
                } else {
                    return new ChromeDriver();
                }
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                if (options.length > 0 && options[0] instanceof FirefoxOptions) {
                    return new FirefoxDriver((FirefoxOptions) options[0]);
                } else {
                    return new FirefoxDriver();
                }
            default:
                throw new RuntimeException("Unsupported browser: " + webDriverName);
        }
    }
}
