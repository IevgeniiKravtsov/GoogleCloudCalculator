package driver;

import org.openqa.selenium.WebDriver;

public interface WebDriverFactory {
    WebDriver createDriverOf(); //

    public default void closeDriver(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }

}
