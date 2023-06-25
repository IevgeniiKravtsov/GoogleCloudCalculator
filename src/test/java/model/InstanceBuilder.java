package model;

public class InstanceBuilder {
    private String instanceName;
    private String numberOfInstances;
    private String goalOfInstances;
    private String operatingSystem;
    private String provisioningModel;
    private String typeOfSeries;
    private String typeOfInstance;
    private String typeOfGPU;
    private String numberOfGPUs;
    private String localSSD;
    private String dataCenterLocation;
    private String committedUsage;
    private String totalEstimatedCostUsd;

    public InstanceBuilder setInstanceName(String instanceName) {
        this.instanceName = instanceName;
        return this;
    }

    public InstanceBuilder setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
        return this;
    }

    public InstanceBuilder setGoalOfInstances(String goalOfInstances) {
        this.goalOfInstances = goalOfInstances;
        return this;
    }

    public InstanceBuilder setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
        return this;
    }

    public InstanceBuilder setProvisioningModel(String provisioningModel) {
        this.provisioningModel = provisioningModel;
        return this;
    }

    public InstanceBuilder setTypeOfSeries(String typeOfSeries) {
        this.typeOfSeries = typeOfSeries;
        return this;
    }

    public InstanceBuilder setTypeOfInstance(String typeOfInstance) {
        this.typeOfInstance = typeOfInstance;
        return this;
    }

    public InstanceBuilder setTypeOfGPU(String typeOfGPU) {
        this.typeOfGPU = typeOfGPU;
        return this;
    }

    public InstanceBuilder setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
        return this;
    }

    public InstanceBuilder setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
        return this;
    }

    public InstanceBuilder setDataCenterLocation(String dataCenterLocation) {
        this.dataCenterLocation = dataCenterLocation;
        return this;
    }

    public InstanceBuilder setCommittedUsage(String committedUsage) {
        this.committedUsage = committedUsage;
        return this;
    }

    public InstanceBuilder setTotalEstimatedCostUsd(String totalEstimatedCostUsd) {
        this.totalEstimatedCostUsd = totalEstimatedCostUsd;
        return this;
    }

    public Instance createInstance() {
        return new Instance(instanceName, numberOfInstances, goalOfInstances, operatingSystem, provisioningModel, typeOfSeries, typeOfInstance, typeOfGPU, numberOfGPUs, localSSD, dataCenterLocation, committedUsage, totalEstimatedCostUsd);
    }
}