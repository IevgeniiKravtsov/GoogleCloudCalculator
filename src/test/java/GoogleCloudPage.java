import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GoogleCloudPage {
    private final By searchButton = By.xpath("//div[@class='devsite-searchbox']");
    private final By searchInput = By.xpath("//input[@name='q']");
    private final String searchItem = ("//div[@class='gs-title'] //*[text()='%s']");
    WebDriver driver;
    WebDriverWait webDriverWait;

    public GoogleCloudPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void findTextInSearchField(String searchText) {
        driver.findElement(searchButton).click();
        driver.findElement(searchInput).sendKeys(searchText+Keys.RETURN);
    }

    public void clickOnTheSearchLink(String searchLink) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(searchItem, searchLink))));
        driver.findElement(By.xpath(String.format(searchItem, searchLink))).click();
    }

}