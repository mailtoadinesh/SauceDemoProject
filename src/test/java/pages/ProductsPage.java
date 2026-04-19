package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import factory.DriverFactory;

public class ProductsPage {
    private WebDriver driver;

    public ProductsPage(){
    	this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath="//button[text()='Add to cart']")
    private WebElement addToCartBtn;

    @FindBy(className="shopping_cart_link")
    private WebElement cartBtn;

    public void addItemToCart(){
        addToCartBtn.click();
    }

    public void goToCart(){
        cartBtn.click();
    }
}