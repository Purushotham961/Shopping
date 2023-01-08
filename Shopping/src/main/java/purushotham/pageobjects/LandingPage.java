package purushotham.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import purushotham.abstractcomponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement login;
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	public ProductsPage loginApplication(String userName, String passWord)
	{
		email.sendKeys("purushothamtest@gmail.com");
		password.sendKeys("Puru@12345");
		login.click();
		ProductsPage productsPage = new ProductsPage(driver);
		return productsPage;
	}
}
