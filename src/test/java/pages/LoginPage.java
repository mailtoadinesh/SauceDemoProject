package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import factory.DriverFactory;

public class LoginPage {
    private WebDriver driver;

    public LoginPage( ){
    	this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="user-name")
    private WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(id="login-button")
    private WebElement loginBtn;

    public void login(String user, String pass){
        username.sendKeys(user);
        password.sendKeys(pass);
        loginBtn.click();
    }
}