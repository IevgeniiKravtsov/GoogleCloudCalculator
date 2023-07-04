package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;

    protected abstract BasePage openPage();
    protected final int WAIT_TIMEOUT_SECONDS = 10;

    protected final Logger logger = LogManager.getRootLogger();


    protected BasePage(WebDriver driver)
    {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(WAIT_TIMEOUT_SECONDS));
    }
}
