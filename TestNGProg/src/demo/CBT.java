package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class CBT {

	private WebDriver driver;

	@BeforeTest
	@Parameters("browser")
	public void openBrowser(String browser) {
		if (browser.equals("Chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equals("InternetExplorer")) {
			driver = new InternetExplorerDriver();
		} else if (browser.equals("MicrosoftEdge")) {
			driver = new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.get("https://facebook.com");
	}

	@Test(priority = 0)
	public void titleVerification() {
		String expectedTitle = "Facebook - Log In or Sign Up";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedTitle);
	}

	@Test(priority=1)
	public void errorMsgVerification() {	
		driver.findElement(By.id("email")).sendKeys("priyankarana151@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("sumit@123");
		driver.findElement(By.id("loginbutton")).click();
		String expectedMessage = "The email you’ve entered doesn’t match any account. Sign up for an account.";
		String actualMessage = driver.findElement(By.cssSelector("#globalContainer > div.uiContextualLayerPositioner._572t.uiLayer > div > div > div"))
			.getText();
		Assert.assertEquals(actualMessage, expectedMessage);
	}
	
	@Test(priority=2)
	public void verifyLoginButtonVisibility() {
		boolean loginButtonStatus = driver.findElement(By.id("u_0_2")).isDisplayed();
		Assert.assertTrue(loginButtonStatus);
	}
	
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}
