package BaseClass;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    public WebDriver driver;


    public PageBase(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void sleep(int seconds) {
        try{
            Thread.sleep(seconds * 1000);
        }catch(InterruptedException e) {

        }
    }
    public void scrollToElement(WebElement element) {
        JavascriptExecutor jsExecutor = ((JavascriptExecutor) driver);
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForVisibility(WebElement element){
        WebDriverWait  wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until((ExpectedConditions.visibilityOf(element)));
    }

    public void enterText(WebElement element, String text){
        waitForVisibility(element);
        element.sendKeys((text));
    }

    public void click(WebElement element){
        waitForVisibility(element);
        element.click();
    }

}
