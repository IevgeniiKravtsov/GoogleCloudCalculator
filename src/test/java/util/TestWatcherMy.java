package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;


public class TestWatcherMy implements TestWatcher {

    private final Logger logger = LogManager.getRootLogger();

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        //TakesScreenshot
    }

}
