package io.mangonet.mgo.protocol;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class IntervalExtension implements AfterEachCallback {
    @Override
    public void afterEach(ExtensionContext context) throws InterruptedException {
        if (isMgoteMode(context)) {
            Thread.sleep(200L);
        }
    }

    private boolean isMgoteMode(ExtensionContext context) {
        return context.getTags().contains("mgote");
    }

}
