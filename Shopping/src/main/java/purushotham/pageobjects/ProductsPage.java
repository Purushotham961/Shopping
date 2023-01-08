package purushotham.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import purushotham.abstractcomponents.AbstractComponent;

public class ProductsPage extends AbstractComponent
{
	WebDriver driver;
	public ProductsPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	@FindBy(css="div[aria-label='Product Added To Cart']")
	WebElement productAddedConfirmationMessage;
	
	By productName = By.cssSelector(".card-body b");
	By addToCart = By.cssSelector("[class*='btn w-10 rounded']");
	
	public List<WebElement> getListOfProducts()
	{
		return products;
	}
	
	public WebElement getProduct(String ProductName)
	{
		WebElement reqProduct = getListOfProducts().stream().filter(product->product.findElement(productName).getText().equalsIgnoreCase(ProductName))
		.findFirst().orElse(null);
		return reqProduct;
	}
	
	public void clickOnAddToCart(String ProductName)
	{
		getProduct(ProductName).findElement(addToCart).click();
		waitForWebElementToDissappear(productAddedConfirmationMessage);
	}
	
}
