package com.course.selenium.hooks;

import com.course.selenium.browserFactory.BrowserFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class Hooks {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return driverThreadLocal.get();
    }

    @Before
    public void setUp(){
        driverThreadLocal.set(BrowserFactory.initializeDriver());
    }

    @After
    public void tearDown(){
        if(driverThreadLocal.get() != null){
            driverThreadLocal.get().quit();
        }
    }
}
