package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

class FirefoxDriverFactory implements WebDriverFactory {
    @Override
    public WebDriver createDriverOf() { //WebDriverType testContext
        WebDriverManager.firefoxdriver().setup();
        return new FirefoxDriver();
    }
}
