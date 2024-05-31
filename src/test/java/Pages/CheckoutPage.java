package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends PageBase {
    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(tagName = "textarea")
    private WebElement commentBox;
    @FindBy(xpath = "//a[text()='Place Order']")
    private WebElement placeOrderBtn;

    //Methods
    public WebElement getCommentBox() {
        return commentBox;
    }
    public void editComment(String text) {
        enterText(commentBox, text);
    }
    public void clickPlaceOrderBtn() {
        click(placeOrderBtn);
    }
}
