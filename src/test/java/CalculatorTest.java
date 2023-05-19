import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    String baseURL = "https://cloud.google.com";
    String calculatorPage = "https://cloud.google.com/products/calculator";
    WebDriver driver;
    GoogleCloudPage googleCloudPage;
    CloudPricingCalculatorPage cloudPricingCalculatorPage;

//    @BeforeAll
    public void setUp() { //setUp local driver or use seleniumGridSetUp for remote test execution
// setUp FireFox driver
/*       System.clearProperty("webdriver.chrome.driver");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
*/
// setUp Chrome driver
        /*        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        */
    }

    @BeforeAll // setUp SeleniumGrid use this or setUp method to setUp the local or remote test execution
    // before running need to set up Selenium Server https://www.selenium.dev/documentation/grid/
    public void seleniumGridSetUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.WIN10);
        caps.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
    }

    @Test
    public void isCloudPricingCalculatorPageOpen() {
        String etalonText = "Google Cloud Platform Pricing Calculator";

        driver.get(baseURL);
        googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPage.findTextInSearchField(etalonText);
        googleCloudPage.clickOnTheSearchLink(etalonText);
        assertEquals("Google Cloud Pricing Calculator", driver.getTitle());
    }

    @Test
    public void isComputeEngineButtonPressed() {
        driver.get(calculatorPage);
        cloudPricingCalculatorPage = new CloudPricingCalculatorPage(driver);
        cloudPricingCalculatorPage.switchToFrame();
        assertEquals(true, cloudPricingCalculatorPage.isComputeEngineButtonPressed());
    }

    @Test
    public void verifyEnteredData() {
        driver.get(calculatorPage);
        cloudPricingCalculatorPage = new CloudPricingCalculatorPage(driver);
        cloudPricingCalculatorPage.switchToFrame();
        cloudPricingCalculatorPage.enterNumberOfInstances("4");
        cloudPricingCalculatorPage.enterGoalOfInstances("");
        cloudPricingCalculatorPage.enterOperatingSystem();
        cloudPricingCalculatorPage.chooseProvisioningModel("Regular");
        cloudPricingCalculatorPage.enterSeries("N1");
        cloudPricingCalculatorPage.enterTypeOfInstance("n1-standard-8");
        cloudPricingCalculatorPage.clickOnAddGPUsCheckBox();
        cloudPricingCalculatorPage.enterGPUType("NVIDIA Tesla V100");
        cloudPricingCalculatorPage.enterNumberOfGPUs("1");
        cloudPricingCalculatorPage.enterLocalSSD();
        cloudPricingCalculatorPage.enterDatacenterLocation("(europe-west3)");
        cloudPricingCalculatorPage.enterCommittedUsage("1 Year");
        cloudPricingCalculatorPage.clickOnAddToEstimateButton();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateProvisioningModel()).isEqualTo(("Regular"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateInstanceType()).isEqualTo(("n1-standard-8\nCommitted Use Discount applied"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateRegion()).isEqualTo(("Frankfurt"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateLocalSSD()).isEqualTo(("2x375 GiB\nCommitted Use Discount applied"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateCommitmentTerm()).isEqualTo(("1 Year"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateTotalInstances()).isEqualTo(("4 x"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getTotalEstimatedCostUsd()).isEqualTo(("USD 1,081.20 per 1 month"));
        softAssertions.assertAll();
    }

    @AfterAll
    public void terDown() {
       driver.quit();
    }
}
