package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AllVideoGamesListingPage extends BasePage{


    public AllVideoGamesListingPage(WebDriver driver) {
        super(driver);
    }

    private By displayedProductsPrices = By.cssSelector(".sg-col-inner .a-price-whole");
    private String displayedPrice = "(//*[contains(@class,'sg-col-inner')]//*[contains(@class,'a-price-whole')])[%s]";
    private By addToCartBtn = By.cssSelector(".a-section #add-to-cart-button") ;


    public void getDisplayedProductsWithPriceBelowThreshold(int thresholdPrice)
    {
        List<WebElement> displayedPrices = locateListOfElements(displayedProductsPrices);
        for (int i = 0; i < displayedPrices.size(); i++) {
            By priceXpath = By.xpath(String.format(displayedPrice, i+1)) ;
            String url = driver.getCurrentUrl();
            WebElement price = locateElement(priceXpath);
            int displayedPriceAsInteger = Integer.parseInt(price.getText().replace(",", "")) ;
            if (displayedPriceAsInteger < thresholdPrice)
            {
                scrollToElement(price);
                clickElement(priceXpath);
                clickElement(addToCartBtn);
                driver.get(url);
            }
        }
    }
}
