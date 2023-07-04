package model;

public class Instance {
    private String instanceName;
    private String numberOfInstances;
    private String goalOfInstances;
    private String OperatingSystem;
    private String ProvisioningModel;
    private String typeOfSeries;
    private String typeOfInstance;
    private String typeOfGPU;
    private String numberOfGPUs;
    private String localSSD;
    private String dataCenterLocation;
    private String committedUsage;

    private String totalEstimatedCostUsd;

    public Instance(String instanceName, String numberOfInstances, String goalOfInstances, String operatingSystem, String provisioningModel, String typeOfSeries, String typeOfInstance, String typeOfGPU, String numberOfGPUs, String localSSD, String dataCenterLocation, String committedUsage, String totalEstimatedCostUsd) {
        this.instanceName = instanceName;
        this.numberOfInstances = numberOfInstances;
        this.goalOfInstances = goalOfInstances;
        OperatingSystem = operatingSystem;
        ProvisioningModel = provisioningModel;
        this.typeOfSeries = typeOfSeries;
        this.typeOfInstance = typeOfInstance;
        this.typeOfGPU = typeOfGPU;
        this.numberOfGPUs = numberOfGPUs;
        this.localSSD = localSSD;
        this.dataCenterLocation = dataCenterLocation;
        this.committedUsage = committedUsage;
        this.totalEstimatedCostUsd = totalEstimatedCostUsd;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getGoalOfInstances() {
        return goalOfInstances;
    }

    public void setGoalOfInstances(String goalOfInstances) {
        this.goalOfInstances = goalOfInstances;
    }

    public String getOperatingSystem() {
        return OperatingSystem;
    }

    public void setOperatingSystem(String operatingSystem) {
        OperatingSystem = operatingSystem;
    }

    public String getProvisioningModel() {
        return ProvisioningModel;
    }

    public void setProvisioningModel(String provisioningModel) {
        ProvisioningModel = provisioningModel;
    }

    public String getTypeOfSeries() {
        return typeOfSeries;
    }

    public void setTypeOfSeries(String typeOfSeries) {
        this.typeOfSeries = typeOfSeries;
    }

    public String getTypeOfInstance() {
        return typeOfInstance;
    }

    public void setTypeOfInstance(String typeOfInstance) {
        this.typeOfInstance = typeOfInstance;
    }

    public String getTypeOfGPU() {
        return typeOfGPU;
    }

    public void setTypeOfGPU(String typeOfGPU) {
        this.typeOfGPU = typeOfGPU;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDataCenterLocation() {
        return dataCenterLocation;
    }

    public void setDataCenterLocation(String dataCenterLocation) {
        this.dataCenterLocation = dataCenterLocation;
    }

    public String getCommittedUsage() {
        return committedUsage;
    }

    public void setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
    }

    public String getTotalEstimatedCostUsd() {
        return totalEstimatedCostUsd;
    }

    public void setTotalEstimatedCostUsd(String totalEstimatedCostUsd) {
        this.totalEstimatedCostUsd = totalEstimatedCostUsd;
    }
}
