package driver;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver createDriverOf(); //

    default void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

}
