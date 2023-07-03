package test;

import driver.DriverSingleton;
import driver.LocalWebDriverFactory;
import driver.WebDriverFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import util.TestWatcherMy;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestWatcherMy.class)
public class CommonConditions {
  WebDriverFactory webDriverFactory = new LocalWebDriverFactory();

  protected WebDriver driver;

    @BeforeAll
    public void setUp() {
//        driver = DriverSingleton.getDriver();
        driver = webDriverFactory.createDriverOf();;

    }

    @AfterAll
    public void terDown() {
 //       DriverSingleton.closeDriver();
      webDriverFactory.closeDriver(driver);

    }

}
