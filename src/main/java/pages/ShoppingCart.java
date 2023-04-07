package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ShoppingCart extends BasePage{


    public ShoppingCart(WebDriver driver) {
        super(driver);
    }
    private By itemsInsideShoppingCart = By.cssSelector("[data-name='Active Items'] .sc-list-item-content");
    private By proceedToCheckoutBtn = By.name("proceedToRetailCheckout");
    private By activeItemPrice = By.cssSelector("[data-name='Active Items'] .sc-item-price-block span") ;
    private By orderTotalPrice = By.cssSelector("#sc-subtotal-amount-activecart .sc-price") ;

    private Integer getPriceWithoutDecimalsOrCurrency (WebElement targetedPrice)
    {
        String activePrice = getTextOfElement(targetedPrice) ;
        String activePriceRemovingDecimals = activePrice.trim().substring(0, activePrice.length() - 3) ;
        int finalPrice = Integer.parseInt(activePriceRemovingDecimals.substring(4).replace(",",""));
        return finalPrice;
    }
    private Integer getPriceWithoutDecimalsOrCurrency (By targetedPrice)
    {
        String activePrice = getTextOfElement(targetedPrice) ;
        String activePriceRemovingDecimals = activePrice.trim().substring(0, activePrice.length() - 3) ;
        int finalPrice = Integer.parseInt(activePriceRemovingDecimals.substring(4).replace(",",""));
        return finalPrice;
    }

    public int getNumberOfItemsInsideShoppingCart ()
    {
        return locateListOfElements(itemsInsideShoppingCart).size();
    }
    public boolean validateThatThereAreItemsInsideCart()
    {
        return getNumberOfItemsInsideShoppingCart() > 0  ;
    }

    public boolean validateThatAddedItemsAreBelowThreshold(int priceThreshold)
    {
        boolean isLessThanThreshold = true;
        List<WebElement> allPricesOfActiveElements = locateListOfElements(this.activeItemPrice);
        for (WebElement priceOfActiveElement: allPricesOfActiveElements)
        {
            int activeItemPrice = getPriceWithoutDecimalsOrCurrency(priceOfActiveElement);
            if (activeItemPrice > priceThreshold)
            {
                isLessThanThreshold = false;
            }
        }
        return isLessThanThreshold;
    }

    public CheckoutPage clickProceedToCheckoutButton ()
    {
        clickElement(proceedToCheckoutBtn);
        return new CheckoutPage (driver);
    }

    public Integer getOrderTotalPrice ()
    {
        int totalPrice = getPriceWithoutDecimalsOrCurrency(orderTotalPrice);
        return totalPrice;
    }
}
