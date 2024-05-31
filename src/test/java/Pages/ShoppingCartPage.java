package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends PageBase {
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='Proceed To Checkout']")
    private WebElement proceedToCheckoutBtn;

    //Methods

    public void clickProceedToCheckout() {
        click(proceedToCheckoutBtn);
    }
}
