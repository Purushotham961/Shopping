package purushotham.tests;

import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import purushotham.pageobjects.CartPage;
import purushotham.pageobjects.ProductsPage;
import purushotham.testcomponents.BaseTest;
import purushotham.testcomponents.Retry;

public class TestByJsonData extends BaseTest
{
	@Test(dataProvider = "getData", retryAnalyzer = Retry.class)
	public void jsonDataTest(HashMap<String, String> input)
	{
		ProductsPage productsPage = landingPage.loginApplication(input.get("email"), input.get("password"));
		productsPage.clickOnAddToCart(input.get("productName1"));
		productsPage.clickOnAddToCart(input.get("productName2"));
		CartPage cartPage = productsPage.clickOnCartHeader();
		Boolean match1 = cartPage.verifyTheProduct(input.get("productName1"));
		Assert.assertTrue(match1);
		Boolean match2 = cartPage.verifyTheProduct(input.get("productName2"));
		Assert.assertTrue(match2);
	}
	
	@DataProvider
	public Object[][] getData() throws Exception
	{
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\purushotham\\data\\SubmitOrderTest.json");
		return new Object[][] {{data.get(0)}};
	}
}
