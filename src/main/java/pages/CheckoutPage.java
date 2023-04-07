package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    private By cashPaymentMethod = By.xpath("//span[contains(@class,'a-label')]/span[text()='Cash on Delivery (COD)']");
    private By usePaymentMethodButton = By.cssSelector(".a-box .apx-compact-continue-action");
    private String pricesInsideOrderSummaryTable =
            "(//*[@id='subtotals-marketplace-table']//td[contains(@class,'a-text-right')])[%s]";

    private Integer getPriceWithoutDecimalsOrCurrency (By targetedPrice)
    {
        String activePrice = getTextOfElement(targetedPrice) ;
        String activePriceRemovingDecimals = activePrice.trim().substring(0, activePrice.length() - 3) ;
        int finalPrice = Integer.parseInt(activePriceRemovingDecimals.substring(4).replace(",",""));
        return finalPrice;
    }
    public void selectCashOnDeliveryAsPayment ()
    {
        forceClickOnElement(cashPaymentMethod);
        clickElement(usePaymentMethodButton);
    }

    public Integer getTotalOrderPriceFromOrderSummarySection ()
    {
        By itemsPrice = By.xpath(String.format(pricesInsideOrderSummaryTable, 1));
        return getPriceWithoutDecimalsOrCurrency(itemsPrice);
    }
}
