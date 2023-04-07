package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultsBarPage extends BasePage{


    public ResultsBarPage(WebDriver driver) {
        super(driver);
    }
    By sortByDropdown = By.cssSelector(".s-widget-container .a-dropdown-label");
    String selection = "//a[@class='a-dropdown-link' and text()='%s']";
    public enum SortDropdownOptions {
        FEATURED("Featured"),
        PRICE_LOW_TO_HIGH("Price: Low to High"),
        PRICE_HIGH_TO_LOW("Price: High to Low");

        private final String option;
        SortDropdownOptions(String option) {
            this.option = option;
        }

        public String getOption() {
            return option;
        }
    }

    public void selectFromSortByDropdown (SortDropdownOptions option)
    {
        clickElement(sortByDropdown);
        clickElement(By.xpath(String.format(selection, option.getOption())));
    }
}
