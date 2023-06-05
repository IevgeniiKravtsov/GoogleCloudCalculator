package service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    private static ResourceBundle resourceBundleData = ResourceBundle.getBundle("data");

    public static String getTestData(String key) {
        return  resourceBundle.getString(key);
    }

    public static String getTestDataData(String key) {
        return resourceBundleData.getString(key);
    }
}
