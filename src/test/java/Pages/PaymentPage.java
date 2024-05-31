package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PaymentPage extends PageBase {
    public PaymentPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(xpath = "//input[@name='name_on_card']")
    private WebElement cardName;
    @FindBy(xpath = "//input[@name='card_number']")
    private WebElement cardNumber;
    @FindBy(xpath = "//input[@name='cvc']")
    private WebElement cvc;
    @FindBy(xpath = "//input[@name='expiry_month']")
    private WebElement expiryMonth;
    @FindBy(xpath = "//input[@name='expiry_year']")
    private WebElement expiryYear;
    @FindBy(id = "submit")
    private WebElement confirmOrderBtn;
    @FindBy(xpath = "//b[text()='Order Placed!']")
    private WebElement orderPlacedElement;
    @FindBy(xpath = "(//b[text()='Order Placed!']/../..)/p")
    private WebElement confirmationText;

    //Methods
    public void enterCardName(String cardName) {
        enterText(this.cardName, cardName);
    }
    public void enterCardNumber(String cardNumber) {
        enterText(this.cardNumber, cardNumber);
    }
    public void enterCvc(String cvc) {
        enterText(this.cvc, cvc);
    }
    public void enterExpiryMonth(String expiryMonth) {
        enterText(this.expiryMonth, expiryMonth);
    }
    public void enterExpiryYear(String expiryYear) {
        enterText(this.expiryYear, expiryYear);
    }
    public void clickConfirmOrderBtn() {
        click(confirmOrderBtn);
    }

    public WebElement getOrderPlacedElement() {
        return orderPlacedElement;
    }

    public WebElement getConfirmationText() {
        return confirmationText;
    }
}
