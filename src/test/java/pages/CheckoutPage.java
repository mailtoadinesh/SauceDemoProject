package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import factory.DriverFactory;

public class CheckoutPage {
    private WebDriver driver;

    public CheckoutPage( ){
    	this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="first-name")
    private WebElement firstName;

    @FindBy(id="last-name")
    private WebElement lastName;

    @FindBy(id="postal-code")
    private WebElement postalCode;

    @FindBy(id="continue")
    private WebElement continueBtn;

    public void fillCheckoutDetails(String fName, String lName, String pCode){
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        postalCode.sendKeys(pCode);
        continueBtn.click();
    }
}