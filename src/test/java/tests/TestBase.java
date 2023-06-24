package tests;


import driverSettigns.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;
import pages.LoginPage;
import pages.NavBar;


import java.io.File;
import java.io.IOException;

import static pages.BasePage.takeScreenshot;

public class TestBase {

    protected volatile static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    @BeforeMethod
    public synchronized void a_setup() {
        driver.set(new DriverFactory().getDriver());
        new LoginPage(driver.get())
                .navigate().login();
        driver.get().manage().window().maximize();
    }

    @AfterMethod(alwaysRun = true)
    public synchronized void z_teardown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(driver.get(), result.getName());
        }
        driver.get().quit();
        driver.remove();
    }


}
