package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class CheckoutTest extends BaseTest {
	@Test
    public void endToEndCheckoutTest() {
        LoginPage loginPage = new LoginPage();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage();
        productsPage.addItemToCart();
        productsPage.goToCart();

        CartPage cartPage = new CartPage();
        cartPage.proceedToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.fillCheckoutDetails("John", "Doe", "12345");
        
    }

  
}