package Test;

import BaseClass.TestBase;
import Pages.*;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import java.lang.reflect.Method;

public class PlaceOrder extends TestBase {

    LoginPage loginPage;
    LandingPage landingPage;
    WomenTopsPage topsPage;
    ShoppingCartPage shoppingCartPage;
    CheckoutPage checkoutPage;
    PaymentPage paymentPage;
    private static final Map<String, Integer> itemsDetail = new TreeMap<>();

    private void initializer() {
        loginPage = new LoginPage(driver);
        landingPage = new LandingPage(driver);
        topsPage = new WomenTopsPage(driver);
        shoppingCartPage = new ShoppingCartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        paymentPage = new PaymentPage(driver);
    }

    @Test(priority = 1, description = "Validate that user is able to navigate to the test URL")
    public void Validate_User_Can_Launch_URL(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");
        initializer();
        Assert.assertEquals(driver.getCurrentUrl(), testUrl);
    }

    @Test(priority = 2, description = "Validate user id able to sign-in with correct credentials")
    public void Validate_Valid_User_Login(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");
        landingPage.clickSignInLink();
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getLogoutLink().isDisplayed());
    }

    @Test(priority = 3, description = "Fetch label and associated price, sort it and print on console")
    public void sort_Items_By_Price_And_Print_On_Console(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");

        for (int i = 0; i < landingPage.getFeatureItemPrice().size(); i += 1) {
            itemsDetail.put(landingPage.getFeatureItemsLabel().get(i), landingPage.getFeatureItemPrice().get(i));
        }

        Stream<Map.Entry<String, Integer>> sortedItems =
                itemsDetail.entrySet().stream().sorted(Map.Entry.comparingByValue());

        sortedItems.forEach((item) -> System.out.println(item.getKey() + ": " + "Rs. " + item.getValue()));
    }

    @Test(priority = 4, description = "Validate user is able to select two items and add to cart")
    public void validate_User_Can_Add_To_Cart(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");

        scrollToElement(landingPage.getWomenCategory());
        landingPage.clickWomenCategory();
        landingPage.clickTopsLink();

        Actions actions = new Actions(driver);
        actions.moveByOffset(200, 10).click().build().perform(); // Removes advert modal

        scrollToElement(landingPage.getFancyGreenTop());
        sleep(3);
        landingPage.clickAddGreenTopToCartBtn();
        try {
            waitForElement(10).until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(topsPage.getModalMessage())));
        }catch (TimeoutException e) {
            System.out.println("Element has already been refreshed");
        }

        Assert.assertEquals(topsPage.getModalMessage().getText(), "Your product has been added to cart.");
        topsPage.clickContinueShopping();
        scrollToElement(landingPage.getSummerWhiteTop());
        landingPage.clickAddSummerWhiteTopToCartBtn();
    }

    @Test(priority = 5, description = "Validate user can proceed to checkout and enter card details")
    public void validate_User_Can_Proceed_To_Checkout_And_Enter_Card(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");
        topsPage.clickViewCartBtn();

        shoppingCartPage.clickProceedToCheckout();

        scrollToElement(checkoutPage.getCommentBox());
        checkoutPage.editComment("Order placed.");
        checkoutPage.clickPlaceOrderBtn();

        paymentPage.enterCardName("John Wich");
        paymentPage.enterCardNumber("4100 0000 0000");
        paymentPage.enterCvc("123");
        paymentPage.enterExpiryMonth("01");
        paymentPage.enterExpiryYear("1900");
        paymentPage.clickConfirmOrderBtn();
    }
    @Test(priority = 6, description = "validate user can confirm order is placed")
    public void validate_User_Can_Confirm_Order(Method method) {
        extent.createTest(method.getName(), method.getAnnotation(Test.class).description()).assignCategory("Purchase-item");
        waitForElement(10).until(ExpectedConditions.visibilityOf(paymentPage.getOrderPlacedElement()));
        Assert.assertEquals(paymentPage.getConfirmationText().getText(), "Congratulations! Your order has been confirmed!");
    }
}
