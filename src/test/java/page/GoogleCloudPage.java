package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class GoogleCloudPage extends BasePage {
    private final By searchButton = By.xpath("//div[@class='devsite-searchbox']");
    private final By searchInput = By.xpath("//input[@name='q']");
    private final String searchItem = ("//div[@class='gs-title'] //*[text()='%s']");

   public GoogleCloudPage(WebDriver driver) {
        super(driver);
   }

    public void findTextInSearchField(String searchText) {
        driver.findElement(searchButton).click();
        driver.findElement(searchInput).sendKeys(searchText+Keys.RETURN);
        logger.info("Find text ["+ searchText+"] in search field");

    }

    public void clickOnTheSearchLink(String searchLink) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(searchItem, searchLink))));
        driver.findElement(By.xpath(String.format(searchItem, searchLink))).click();
        logger.info("Click on the link ["+searchLink+"]");
    }

    @Override
    protected BasePage openPage() {
        return this;
    }
}
