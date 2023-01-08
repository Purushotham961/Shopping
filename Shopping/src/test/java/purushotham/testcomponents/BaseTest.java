package purushotham.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import purushotham.pageobjects.LandingPage;

public class BaseTest 
{
	public WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initializeDriver() throws Exception
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\purushotham\\resources\\GlobalData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver = new EdgeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws Exception
	{
		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void closeBrowser()
	{
		driver.quit();
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws Exception
	{
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir")+"//Screenshots//"+testCaseName+".png");
		FileUtils.copyFile(srcFile, destFile);
		return System.getProperty("user.dir")+"//Screenshots//"+testCaseName+".png";
	}
	
	public List<HashMap<String,String>> getJsonDataToMap(String filePath) throws Exception
	{
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		
		//String to HashMap- Jackson Datbind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
		});
		return data;
	}
}
