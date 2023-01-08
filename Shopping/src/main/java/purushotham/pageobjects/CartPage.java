package purushotham.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import purushotham.abstractcomponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> products;
	
	By productAdded = By.cssSelector(".cartSection h3");
	
	public List<WebElement> getListOfProductsAdded()
	{
		waitForListOfWebElementsToAppear(products);
		return products;
	}
	public Boolean verifyTheProduct(String ProductName)
	{
		Boolean match = getListOfProductsAdded().stream().anyMatch(product->product.getText().equalsIgnoreCase(ProductName));
		return match;
	}
}
