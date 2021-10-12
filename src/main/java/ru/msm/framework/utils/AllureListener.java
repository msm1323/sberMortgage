package ru.msm.framework.utils;

import io.cucumber.plugin.event.*;
import io.qameta.allure.Attachment;
import io.qameta.allure.cucumber5jvm.AllureCucumber5Jvm;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import ru.msm.framework.managers.DriverManager;

public class AllureListener extends AllureCucumber5Jvm implements TestWatcher{   //implements TestWatcher

    DriverManager DRIVER_MANAGER = DriverManager.getINSTANCE();

    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] getScreenshot() {
        return ((TakesScreenshot) DRIVER_MANAGER.getDriver()).getScreenshotAs(OutputType.BYTES);
    }

//    @Override
//    public void setEventPublisher(final EventPublisher publisher) {
//        publisher.registerHandlerFor(TestCaseFinished.class, event -> {
//            if (event.getResult().getError() != null) { //?
//                getScreenshot();
//            }
//        });
//        super.setEventPublisher(publisher);
//    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
            getScreenshot();
    }

//    @Override
//    public void afterTestExecution(ExtensionContext context) {
//        if(context.getExecutionException().isPresent()){
//            getScreenshot();
//        }
//    }
}
