package testComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import pageObjects.LandingPage;

public class BaseTest {
	
	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeBrowser() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
		prop.load(fis);
		String browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		//String browser = prop.getProperty("browser");
		
		if (browser.contains("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			if(browser.contains("headless"))
			{
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900));
		}
		else if (browser.equals("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		else if (browser.equals("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		else if (browser.equals("safari"))
		{
			WebDriverManager.safaridriver();
			driver = new SafariDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver , Duration.ofSeconds(5));
		return driver;
	}
	
	public List<HashMap<String, String>> getDataToMap(String filePath) throws IOException
	{
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		
		// convert to hashmap --> jackson databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){});
		return data;
	}
	
	public String getScreenshot(String testCaseName , WebDriver driver) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File ss = ts.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(ss, destination);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
	
	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException
	{
		driver  = initializeBrowser();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	
	@AfterMethod(alwaysRun = true)
	public void tearDown()
	{
		driver.close();
	}
}
