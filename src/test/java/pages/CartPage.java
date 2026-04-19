package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import factory.DriverFactory;

public class CartPage {
    private WebDriver driver;

    public CartPage(){
    	this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="checkout")
    private WebElement checkoutBtn;

    public void proceedToCheckout(){
        checkoutBtn.click();
    }
}