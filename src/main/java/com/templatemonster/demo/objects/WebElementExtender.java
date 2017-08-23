package com.templatemonster.demo.objects;

import org.apache.commons.lang.time.StopWatch;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.internal.WrapsDriver;

public class WebElementExtender {

    public static WebElement setAttributeToTheElement(WebElement element, String attributeName, String value) {
        WrapsDriver wrappedElement = (WrapsDriver) element;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) wrappedElement.getWrappedDriver();
        jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
        return element;
    }

    public static void highlightElement(WebElement element) throws InterruptedException {
        WrapsDriver wrappedElement = (WrapsDriver) element;
        JavascriptExecutor jsExecutor = (JavascriptExecutor) wrappedElement.getWrappedDriver();
        for (int i = 0; i < 5; i++) {
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "color:green; border:2px solid yellow");
            Thread.sleep(100);
            jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, "");
            Thread.sleep(100);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Thread.sleep(1000);
        stopWatch.stop();
        long timeWaited = stopWatch.getTime();
        System.out.println("Time difference in milliseconds is: " + timeWaited);
    }
}
