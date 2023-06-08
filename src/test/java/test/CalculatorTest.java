package test;

import model.Instance;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.*;
import page.CloudPricingCalculatorPage;
import page.GoogleCloudPage;
import service.InstanceCreation;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest extends CommonConditions{

    String baseURL = "https://cloud.google.com";
    String calculatorPage = "https://cloud.google.com/products/calculator";
    GoogleCloudPage googleCloudPage;
    CloudPricingCalculatorPage cloudPricingCalculatorPage;


    @Test
    @Tag("Smoke")
    @Tag("Regression")
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
    @Tag("Regression")
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

        softAssertions.assertThat(cloudPricingCalculatorPage.getTotalEstimatedCostUsd()).isEqualTo((testInstance.getTotalEstimatedCostUsd()));
        softAssertions.assertAll();

    }

}
