package service;

import model.Instance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InstanceCreation {

    private static final Logger logger = LogManager.getRootLogger();
    public static final String INSTANCE_NAME = "env.name";
    public static final String NUMBER_OF_INSTANCES = "testdata.instance.numberOfInstances";
    public static final String GOAL_OF_INSTANCES = "env.name";
    public static final String PROVISIONING_MODEL ="testdata.instance.provisioningModel";
    public static final String SERIES = "testdata.instance.series";
    public static final String TYPE_OF_INSTANCES ="testdata.instance.typeOfInstance";
    public static final String GPU_TYPE = "testdata.instance.gpuType";
    public static final String NUMBER_OF_GPUs = "testdata.instance.numberOfGPUs";
    public static final String DATA_CENTER_LOCATION = "testdata.instance.datacenterLocation";
    public static final String COMMITTED_USAGE = "testdata.instance.committedUsage";
    public static final String TOTAL_ESTIMATED_COST_USD = "testdata.instance.totalEstimatedCostUsd";


    public static Instance withDataFromProperty() {
        Instance instance = new Instance();
        instance.setInstanceName(TestDataReader.getTestData(INSTANCE_NAME));
        instance.setGoalOfInstances(TestDataReader.getTestData(GOAL_OF_INSTANCES));
        instance.setNumberOfInstances(TestDataReader.getTestData(NUMBER_OF_INSTANCES));
        instance.setProvisioningModel(TestDataReader.getTestDataData(PROVISIONING_MODEL));
        instance.setTotalEstimatedCostUsd(TestDataReader.getTestData(TOTAL_ESTIMATED_COST_USD));

        instance.setTypeOfSeries(TestDataReader.getTestDataData(SERIES));
        instance.setTypeOfInstance(TestDataReader.getTestDataData(TYPE_OF_INSTANCES));
        instance.setTypeOfGPU(TestDataReader.getTestDataData(GPU_TYPE));
        instance.setNumberOfGPUs(TestDataReader.getTestDataData(NUMBER_OF_GPUs));
        instance.setDataCenterLocation(TestDataReader.getTestDataData(DATA_CENTER_LOCATION));
        instance.setCommittedUsage(TestDataReader.getTestDataData(COMMITTED_USAGE));

        logger.info(TestDataReader.getTestData(GOAL_OF_INSTANCES)+" env is chosen with $$ "+TestDataReader.getTestData(TOTAL_ESTIMATED_COST_USD));

        return instance;
    }
}
