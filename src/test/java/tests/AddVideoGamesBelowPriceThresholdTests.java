package tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;


import static filesReaders.ReadFromFiles.*;

public class AddVideoGamesBelowPriceThresholdTests extends TestBase{
    NavigationBarPage navigationBarPage;
    int priceThreshold;

    @BeforeClass(description = "read test data")
    public void readTestData ()
    {
        priceThreshold = Integer.parseInt(
                getJsonStringValueByKey("itemsBelowThreshold.json", "priceThreshold") );
    }
    @BeforeMethod(description = "Login to app as pre-condition")
    public void setUpPrecondition_loginToAmazon ()
    {
        navigationBarPage = new NavigationBarPage(driver);
        LoginPage loginPage = navigationBarPage.clickLoginIconFromNavigationBar();
        loginPage.loginToAmazonWebsite(getPropertyByKey("environment.properties", "PHONE"),
                getPropertyByKey("environment.properties", "PASSWORD"));
    }

    @Test(description = "Add all video games below threshold in the first video games listing page")
    public void testAddVideoGamesBelowCertainPrice ()
    {
        AllSideMenuPage allSideMenuPage = navigationBarPage.clickAllFromNavigationBar();
        VideoGamesSideMenu videoGamesSideMenu = allSideMenuPage.clickVideoGamesLink();
        videoGamesSideMenu.clickAllVideoGamesLink();
        ListingLeftSideMenu listingLeftSideMenu = new ListingLeftSideMenu(driver);
        listingLeftSideMenu.clickFreeShipping();
        listingLeftSideMenu.clickNewCondition();
        ResultsBarPage resultsBarPage = new ResultsBarPage(driver);
        resultsBarPage.selectFromSortByDropdown(ResultsBarPage.SortDropdownOptions.PRICE_HIGH_TO_LOW);
        AllVideoGamesListingPage allVideoGamesListingPage = new AllVideoGamesListingPage(driver);
        allVideoGamesListingPage.getDisplayedProductsWithPriceBelowThreshold(priceThreshold);
        ShoppingCart shoppingCart = navigationBarPage.clickCartIconFromNavigationBar();
        Assert.assertTrue(shoppingCart.validateThatThereAreItemsInsideCart(),
                "the items inside shopping cart should be more than zero items");
        Assert.assertTrue(shoppingCart.validateThatAddedItemsAreBelowThreshold(priceThreshold),
                "All The added items prices should be less than " + priceThreshold);
        int orderTotalPriceFromShoppingCart = shoppingCart.getOrderTotalPrice();
        CheckoutPage checkoutPage = shoppingCart.clickProceedToCheckoutButton();
        checkoutPage.selectCashOnDeliveryAsPayment();
        Assert.assertEquals(orderTotalPriceFromShoppingCart, (int) checkoutPage.getTotalOrderPriceFromOrderSummarySection(),
                "total price of items should be the same in checkout page and shopping cart page");
    }
}
