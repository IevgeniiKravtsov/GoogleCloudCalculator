package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSingleton {
    private static WebDriver driver;

    private static final Logger logger = LogManager.getRootLogger();

    private DriverSingleton(){}

    public static WebDriver getDriver(){ ///
        if (null == driver){
            switch (System.getProperty("browser")){
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    logger.info("FireFox driver is chosen, test suits: "+System.getProperty("groups"));
                    break;
                }
                case "edge":{
                    WebDriverManager.firefoxdriver().setup();
                    driver = new EdgeDriver();
                    logger.info("Edge driver is chosen, test suits: "+System.getProperty("groups"));
                    break;
                }
                case "chrome":{
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(options);
                    logger.info("Chrome driver is chosen, test suits: "+System.getProperty("groups"));
                    break;
                }
                default: {};
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver(){
        driver.quit();
        driver = null;
    }
}
