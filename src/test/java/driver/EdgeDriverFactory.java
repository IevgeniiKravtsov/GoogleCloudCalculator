package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

class EdgeDriverFactory implements WebDriverFactory {

    @Override
    public WebDriver createDriverOf() { //WebDriverType webDriverType
        WebDriverManager.firefoxdriver().setup();
        return new EdgeDriver();
    }
}
