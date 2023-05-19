import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CloudPricingCalculatorPage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    private final By computeEngineButton = By.xpath("(//md-pagination-wrapper/md-tab-item)[1]");
    private final By numberOfInstances = By.xpath("//*[contains(text(),'Number of instances')]/..//input[@name='quantity']");
    private final By whatAreTheseInstancesFor = By.xpath("//*[contains(text(),'What are these instances for?')]/..//input[@name='label']");
    private final By operatingSystemX = By.xpath("//md-select-value/span/div[contains(text(),'Free')]/../../..");
    private final By provisioningModel = By.xpath("//*[contains(text(),'Provisioning ')]/..//md-select-value[@class='md-select-value']");
    private final String provisioningModelRegular = "//md-option/div[contains(text(),'%s')]";
    private final By instanceSeries = By.xpath("//*[contains(text(),'Series')]/..//md-select-value[@class='md-select-value']");
    private final String instanceSeriesItem = "//md-option/div[contains(text(),'%s')]";
    private final By instanceType = By.xpath("//*[contains(text(),'Machine type')]/..//md-select-value[@class='md-select-value']");
    private final String  instanceTypeItem = "//md-option/div[contains(text(),'%s')]";
    private final By addGPUs = By.xpath("//md-checkbox[@aria-label='Add GPUs']");
    private final By gpuType = By.xpath("//md-select[@placeholder='GPU type']");
    private final String gpuTypeItem ="//div[@class='md-text ng-binding'][contains(text(),'%s')]";
    private final By numberOfGPUs = By.xpath("//md-select[@placeholder='Number of GPUs']");
    private final String numberOfGPUsItem = "//span[text()='home']/following-sibling::div[contains(@class,'md-active')]//md-option[@value='%s']";
    private final By localSSD = By.xpath("//md-select[@placeholder='Local SSD']");
    //private final By localSSD2_375 = By.xpath("//span[text()='home']/following-sibling::div[contains(@class,'md-active')]//md-option[@value='2']");
    private final By localSSD2_375 = By.xpath("(//div[@class='md-text ng-binding'][contains(text(),'4x375')]) [2]");
    private final By dataCenterLocation = By.xpath("//md-select[@placeholder='Datacenter location']");
    private final String dataCenterLocationItem = ("(//md-option/div[contains(text(),'%s')])[3]");
    private final By committedUsage = By.xpath("//md-select[@placeholder='Committed usage']");
    private final String committedUsageItem = ("(//md-option/div[@class='md-text'][contains(text(),'%s')])[2]");
    //private final By addToEstimateButton = By.xpath("//h2[text()='Instances']/..//button[contains(text(),'Add to Estimate')]");
    private final By addToEstimateButton = By.xpath("//button[contains(text(),' Estimate')]");
    private final By estimateTotalEstimatedCost = By.xpath("//b[contains(text(),'Total Estimated Cost:')]");
    private final By estimateTotalInstances = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/div/div/div/div/span");
    private final By estimateProvisioningModel  = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/md-list-item/div[contains(text(),'Provisioning model')]");
    private final By estimateInstanceType  = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/md-list-item/div[contains(text(),'Instance type')]");
    private final By estimateRegion  = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/md-list-item/div[contains(text(),'Region:')]");
    private final By estimateLocalSSD  = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/md-list-item/div[contains(text(),'Local SSD')]");
    private final By estimateCommitmentTerm  = By.xpath("//h2/span[text()='Compute Engine']/../../following-sibling::md-list/md-list-item/div[contains(text(),'Commitment term:')]");

    public CloudPricingCalculatorPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public String separateByDoubleDots (String inputString) {
        String[] parts = inputString.split(":");
        return parts[1].trim();
    }
    public void switchToFrame() {
        WebElement mainFrame = driver.findElement(By.xpath("//iframe"));
        driver.switchTo().frame(mainFrame);
        WebElement myFrame = driver.findElement(By.xpath("//*[@id='myFrame']"));
        driver.switchTo().frame(myFrame);
    }

    public boolean isComputeEngineButtonPressed (){

        return  Boolean.parseBoolean(webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(computeEngineButton))
                .getAttribute("aria-selected"));
    }
    public void enterNumberOfInstances(String quantityOfInstances){
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(numberOfInstances))
                .sendKeys(quantityOfInstances);
    }
    public void enterGoalOfInstances (String goal){
        driver.findElement(whatAreTheseInstancesFor).sendKeys(goal);
    }
    public void enterOperatingSystem () {
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(operatingSystemX))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath("//md-option/div[contains(text(),'Free')]/..")))
                .click();
    }
    public void chooseProvisioningModel (String pModel){
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(provisioningModel))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(provisioningModelRegular, pModel))))
                .click();
    }
    public  void enterSeries (String series) {
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(instanceSeries))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable((By.xpath(String.format(instanceSeriesItem, series)))))
                .click();
    }
    public void  enterTypeOfInstance (String insType) {
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(instanceType))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(instanceTypeItem, insType))))
                .click();
    }
    public void clickOnAddGPUsCheckBox(){
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(addGPUs))
                .click();
    }
    public void enterGPUType (String type){
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(gpuType))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(gpuTypeItem, type))))
                .click();
    }
    public void enterNumberOfGPUs (String number) {
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(numberOfGPUs))
                .click();
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(numberOfGPUsItem, number))))
                .click();
    }
    public void enterLocalSSD (){
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(localSSD))
                .click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(localSSD2_375))
                .click();
    }
    public void enterDatacenterLocation (String location){
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(dataCenterLocation))
                .click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(dataCenterLocationItem, location))))
                .click();
    }

    public void enterCommittedUsage (String usage) {
        webDriverWait
                .until(ExpectedConditions.presenceOfElementLocated(committedUsage))
                .click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format(committedUsageItem, usage))))
                .click();
    }
    public void clickOnAddToEstimateButton (){
        webDriverWait
                .until(ExpectedConditions.elementToBeClickable(addToEstimateButton)) //presenceOfElementLocated
                .click();
    }
    public String getTotalEstimatedCostUsd(){
        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateTotalEstimatedCost)).getText());
    }
    public String getEstimateTotalInstances() {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateTotalInstances)).getText();
    }
    public String getEstimateProvisioningModel(){

        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateProvisioningModel)).getText());
    }
    public String getEstimateInstanceType() {
        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateInstanceType)).getText());
    }
    public String getEstimateRegion() {
        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateRegion)).getText());
    }
    public String getEstimateLocalSSD() {
        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateLocalSSD)).getText());
    }

    public String getEstimateCommitmentTerm() {
        return separateByDoubleDots(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(estimateCommitmentTerm)).getText());
    }

}
