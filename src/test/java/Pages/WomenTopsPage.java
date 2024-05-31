package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WomenTopsPage extends PageBase {
    public WomenTopsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "(//div[@class='modal-body']/p)[1]")
    private WebElement modalMessage;
    @FindBy(xpath = "//button[@data-dismiss='modal']")
    private WebElement continueShoppingBtn;
    @FindBy(xpath = "(//div[@class='modal-body']/p)[2]")
    private WebElement viewCartBtn;

    //Methods

    public WebElement getModalMessage() {
        return modalMessage;
    }
    public void clickContinueShopping() {
        click(continueShoppingBtn);
    }
    public void clickViewCartBtn() {
        click(viewCartBtn);
    }
}
