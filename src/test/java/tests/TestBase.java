package tests;


import driverSettigns.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.HomePage;


import static pages.BasePage.takeScreenshot;

public class TestBase {

    protected static WebDriver driver;
    protected HomePage homePage;



    @BeforeMethod
    public void setup () {
        driver= DriverFactory.getDriver() ;
        // start of application
        homePage=new HomePage(driver);
        homePage.navigateToHomePage();
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void teardown (ITestResult result)
    {
        if (result.getStatus() == ITestResult.FAILURE) {
            takeScreenshot(driver, result.getName() );
        }
        driver.quit();
    }


}
