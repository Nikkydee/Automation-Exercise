package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

public class LandingPage extends PageBase {

    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//li/a[@href='/login']")
    private WebElement loginLink;

    @FindBy(xpath = "((//div[@class='features_items'])/div[@class='col-sm-4']/*)/div[1]/div/p")
    private List<WebElement> featureItemsLabel;

    @FindBy(xpath = "((//div[@class='features_items'])/div[@class='col-sm-4']/*)/div[1]/div/h2")
    private List<WebElement> featureItemPrice;
    @FindBy(xpath = "//a[@href='#Women']")
    private WebElement womenCategory;
    @FindBy(xpath = "//a[text()='Tops ']")
    private WebElement topsLink;
    @FindBy(xpath = "(//p[text()='Fancy Green Top'])[1]")
    private WebElement fancyGreenTop;
    @FindBy(xpath = "(//p[text()='Summer White Top'])[1]")
    private WebElement summerWhiteTop;
    @FindBy(xpath = "(//p[text()='Fancy Green Top'])[1]")
    private List<WebElement> fancyGreenTopArray;
    @FindBy(xpath = "(//div/a[@data-product-id='8'])[1]")
    private WebElement addFancyGreenTopToCartBtn;
    @FindBy(xpath = "(//div/a[@data-product-id='6'])[1]")
    private WebElement addSummerWhiteTopToCartBtn;
    @FindBy(xpath = "(//span[text()='Close'])")
    private WebElement closeBtn;
    @FindBy(xpath = "(//iframe[@title='Advertisement'])[1]")
    private WebElement adIframe;

    public void clickSignInLink() {
        click(loginLink);
    }
    public List<String> getFeatureItemsLabel() {
        return featureItemsLabel.stream().map(label -> label.getText()).collect(Collectors.toList());
    }
    public List<Integer> getFeatureItemPrice() {
        return featureItemPrice.stream().map(price -> Integer.valueOf(price.getText().split(" ")[1])).collect(Collectors.toList());
    }
    public void clickWomenCategory() {
        click(womenCategory);
    }
    public WebElement getWomenCategory() {
        return womenCategory;
    }
    public void clickTopsLink() {
        click(topsLink);
        sleep(3);
    }
    public WebElement getFancyGreenTop() {
        return fancyGreenTop;
    }
    public WebElement getSummerWhiteTop() {
        return summerWhiteTop;
    }
    public void clickAddGreenTopToCartBtn() {

        int attempts = 0;
        while(attempts < 3) {
            try {
                click(addFancyGreenTopToCartBtn);
                break;
            }catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                scrollToElement(fancyGreenTop);
                System.out.println("Stale element reference exception encountered, driver will try " + (2 - attempts) + " more times");
            }
            attempts++;
        }
    }
    public void clickAddSummerWhiteTopToCartBtn() {
        click(addSummerWhiteTopToCartBtn);
    }

    public List<WebElement> getFancyGreenTopArray() {
        return fancyGreenTopArray;
    }

    public WebElement getCloseBtn() {
        return closeBtn;
    }
    public void clickCloseBtn() {
        click(closeBtn);
    }

    public WebElement getAdIframe() {
        return adIframe;
    }
}
