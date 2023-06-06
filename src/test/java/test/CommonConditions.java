package test;

import driver.DriverSingleton;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import util.TestWatcherMy;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(TestWatcherMy.class)
public class CommonConditions {

  protected WebDriver driver;

    @BeforeAll
    public void setUp() {
        driver = DriverSingleton.getDriver();
    }

    @AfterAll
    public void terDown() {
        DriverSingleton.closeDriver();

    }

}
