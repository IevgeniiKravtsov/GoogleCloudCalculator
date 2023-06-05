package test;

import driver.DriverSingleton;

import model.Instance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.StringUtils;
import org.assertj.core.api.SoftAssertions;

import org.junit.jupiter.api.*;

import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.WebDriver;

import page.CloudPricingCalculatorPage;
import page.GoogleCloudPage;
import service.InstanceCreation;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {

    String baseURL = "https://cloud.google.com";
    String calculatorPage = "https://cloud.google.com/products/calculator";
    WebDriver driver;
    GoogleCloudPage googleCloudPage;
    CloudPricingCalculatorPage cloudPricingCalculatorPage;

    private final Logger logger = LogManager.getRootLogger();

    @BeforeAll
    public void setUp() { //setUp local driver or use seleniumGridSetUp for remote test execution
        driver = DriverSingleton.getDriver();


// setUp FireFox driver
/*       System.clearProperty("webdriver.chrome.driver");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
*/
// setUp Chrome driver
/*        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options); */

    }

/*    @BeforeAll // setUp SeleniumGrid use this or setUp method to setUp the local or remote test execution
    // before running need to set up Selenium Server https://www.selenium.dev/documentation/grid/
    public void seleniumGridSetUp() throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setPlatform(Platform.WIN10);
        caps.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
    } */

    @Test
    @Tag("Smoke")
    public void isCloudPricingCalculatorPageOpen() {
        String etalonText = "Google Cloud Platform Pricing Calculator";

        driver.get(baseURL);
        googleCloudPage = new GoogleCloudPage(driver);
        googleCloudPage.findTextInSearchField(etalonText);
        googleCloudPage.clickOnTheSearchLink(etalonText);
        assertEquals("Google Cloud Pricing Calculator", driver.getTitle());
    }

    @Test
    @Tag("Smoke")
    public void isComputeEngineButtonPressed() {
        driver.get(calculatorPage);
        cloudPricingCalculatorPage = new CloudPricingCalculatorPage(driver);
        cloudPricingCalculatorPage.switchToFrame();
        Assertions.assertEquals(true, cloudPricingCalculatorPage.isComputeEngineButtonPressed());
    }

    @Test
    @Tag("Regression")
    public void verifyEnteredData() {
        driver.get(calculatorPage);
        cloudPricingCalculatorPage = new CloudPricingCalculatorPage(driver);
        cloudPricingCalculatorPage.switchToFrame();

        Instance testInstance = InstanceCreation.withDataFromProperty();

        cloudPricingCalculatorPage.enterNumberOfInstances(testInstance.getNumberOfInstances());
        cloudPricingCalculatorPage.enterGoalOfInstances(testInstance.getGoalOfInstances());
        cloudPricingCalculatorPage.enterOperatingSystem();
        cloudPricingCalculatorPage.chooseProvisioningModel(testInstance.getProvisioningModel()); //"Regular"
        cloudPricingCalculatorPage.enterSeries(testInstance.getTypeOfSeries()); //"N1"
        cloudPricingCalculatorPage.enterTypeOfInstance(testInstance.getTypeOfInstance()); //"n1-standard-8"
        cloudPricingCalculatorPage.clickOnAddGPUsCheckBox();
        cloudPricingCalculatorPage.enterGPUType(testInstance.getTypeOfGPU()); // "NVIDIA Tesla V100"
        cloudPricingCalculatorPage.enterNumberOfGPUs(testInstance.getNumberOfGPUs()); //"1"
        cloudPricingCalculatorPage.enterLocalSSD();
        cloudPricingCalculatorPage.enterDatacenterLocation(testInstance.getDataCenterLocation()); //"(europe-west3)"
        cloudPricingCalculatorPage.enterCommittedUsage(testInstance.getCommittedUsage()); //"1 Year"
        cloudPricingCalculatorPage.clickOnAddToEstimateButton();

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateProvisioningModel()).isEqualTo(("Regular"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateInstanceType()).isEqualTo(("n1-standard-8\nCommitted Use Discount applied"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateRegion()).isEqualTo(("Frankfurt"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateLocalSSD()).isEqualTo(("24x375 GiB\nCommitted Use Discount applied"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateCommitmentTerm()).isEqualTo(("1 Year"));
        softAssertions.assertThat(cloudPricingCalculatorPage.getEstimateTotalInstances()).isEqualTo((testInstance.getNumberOfInstances()+" x "+testInstance.getGoalOfInstances()));

        softAssertions.assertThat(cloudPricingCalculatorPage.getTotalEstimatedCostUsd()).isEqualTo(("USD 1,081.20 per 1 month"));
        softAssertions.assertAll();
    }

    @AfterAll
    public void terDown() {
       DriverSingleton.closeDriver();

    }

    @RegisterExtension
    public TestWatcher watcher = new TestWatcher() {
        @Override
        public void testFailed(ExtensionContext context, Throwable cause) {
            // Take screenshot on test failure
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Path screenshotPath = Paths.get("screenshots", context.getTestClass().get().getSimpleName(), StringUtils.getCurrentTimeAsString() +"_"+ context.getTestMethod().get().getName() + ".png");
            logger.info(screenshotPath+" screenShot saved");

            try {
                Files.createDirectories(screenshotPath.getParent());
                Files.move(screenshot.toPath(), screenshotPath);
  //              System.out.println("Screenshot saved to " + screenshotPath); rewrite to logger
            } catch (IOException ex) {
 //               System.err.println("Failed to save screenshot: " + ex.getMessage());
            }
        }
    };

}
