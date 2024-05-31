package Pages;

import BaseClass.PageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//input[@data-qa='login-email']")
    private WebElement usernameField;

    @FindBy(xpath="//input[@data-qa='login-password']")
    private WebElement passwordField;

    @FindBy(xpath= "//button[@data-qa='login-button']")
    private WebElement loginBtn;
    @FindBy(xpath = "//li/a[@href='/logout']")
    private WebElement logoutLink;

    public WebElement getUsernameField(){
        return usernameField;
    }

    public WebElement getPasswordField(){
        return passwordField;
    }

    public void enterUsername(String email){
        enterText(usernameField,email);
    }

    public void enterPassword(String password){
        enterText(passwordField,password);
    }

    public WebElement getLoginButton(){

        return loginBtn;
    }

    public void clickLoginButton(){
        click(loginBtn);
    }
    public WebElement getLogoutLink() {
        return logoutLink;
    }
}
