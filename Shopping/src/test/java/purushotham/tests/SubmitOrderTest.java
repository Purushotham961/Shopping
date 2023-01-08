package purushotham.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import purushotham.pageobjects.CartPage;
import purushotham.pageobjects.ProductsPage;
import purushotham.testcomponents.BaseTest;
import purushotham.testcomponents.Retry;

public class SubmitOrderTest extends BaseTest
{
	@Test(retryAnalyzer = Retry.class)
	public void submitOrder()
	{
		ProductsPage productsPage = landingPage.loginApplication("purushothamtest@gmail.com", "Puru@12345");
		productsPage.clickOnAddToCart("ADIDAS ORIGINAL");
		productsPage.clickOnAddToCart("ZARA COAT 3");
		CartPage cartPage = productsPage.clickOnCartHeader();
		Boolean match1 = cartPage.verifyTheProduct("ADIDAS ORIGINAL");
		Assert.assertTrue(match1);
		Boolean match2 = cartPage.verifyTheProduct("ZARA COAT 3");
		Assert.assertTrue(match2);
	}
}
