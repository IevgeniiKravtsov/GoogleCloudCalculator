package driver;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class LocalWebDriverFactory implements WebDriverFactory {
    private static final Logger logger = LogManager.getRootLogger();
    private final WebDriverFactory firefoxDriverFactory = new FirefoxDriverFactory();
    private final WebDriverFactory chromeDriverFactory = new ChromeDriverFactory();
    private final WebDriverFactory edgeDriverFactory = new EdgeDriverFactory();

    @Override
    public WebDriver createDriverOf() {
        WebDriverFactory webDriverFactory = null;
        switch (System.getProperty("browser")) {
            case "firefox": {
                webDriverFactory = firefoxDriverFactory;
                break;
            }
            case "edge": {
                //
                webDriverFactory = edgeDriverFactory;
                break;
            }
            case "chrome": {
                webDriverFactory = chromeDriverFactory;
                break;
            }
            default: {
            }

        }
        logger.info(System.getProperty("browser") + " driver is chosen, test suits: " + System.getProperty("groups"));
        return webDriverFactory.createDriverOf();

    }

}
