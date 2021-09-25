
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class LoginPage {
	static String button = "btnLogin";
	static ChromeDriver driver;

	@Test
	static void correctId_correctPass() {
		System.setProperty("webdriver.chrome.driver", "C:/Selenium/chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(Details.BASE_URL);

		driver.findElement(By.name("btnReset")).click();
		System.out.println("Both userid and password correct");
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(Details.USER_ID);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Details.PASSWORD);
		driver.findElement(By.name(button)).click();

		Alert alert = driver.switchTo().alert();
		alert.accept();

	}

	@Test
	static void correctId_wrongPass() {

		driver.get(Details.BASE_URL);
		System.out.println("Trying for wrong password and correct id");
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(Details.USER_ID);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Details.INVALID_PASS);
		driver.findElement(By.name(button)).click();
		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			assertEquals(alertMsg, Details.EXPECTED_ERROR);
		} catch (NoAlertPresentException ex) {
			String actualTitle = driver.getTitle();
			assertEquals(actualTitle, Details.EXPECTED_TITLE);

		}

	}

	@Test
	static void correctPass_wrongId() {
		System.out.println("Trying for wrong id and correct password");
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("uid")).sendKeys(Details.INVALID_UID);
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Details.PASSWORD);
		driver.findElement(By.name(button)).click();

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			assertEquals(alertMsg, Details.EXPECTED_ERROR);
		} catch (NoAlertPresentException ex) {
			String actualTitle = driver.getTitle();
			assertEquals(actualTitle, Details.EXPECTED_TITLE);
		}

	}

	@Test
	static void userIdEmpty() {

		System.out.println("Trying to keep userID field empty");
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("password")).sendKeys(Details.PASSWORD);
		driver.findElement(By.name(button)).click();

		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			assertEquals(alertMsg, Details.EXPECTED_ERROR);
		} catch (NoAlertPresentException ex) {
			String actualTitle = driver.getTitle();
			assertEquals(actualTitle, Details.EXPECTED_TITLE);
		}
	}

	@Test
	static void passwordEmpty() {
		System.out.println("Trying to keep password field empty");
		driver.findElement(By.name("uid")).clear();
		driver.findElement(By.name("password")).clear();
		driver.findElement(By.name("uid")).sendKeys(Details.USER_ID);
		driver.findElement(By.name(button)).click();
		try {
			Alert alert = driver.switchTo().alert();
			String alertMsg = alert.getText();
			alert.accept();
			assertEquals(alertMsg, Details.EXPECTED_ERROR);
		} catch (NoAlertPresentException ex) {
			String actualTitle = driver.getTitle();
			assertEquals(actualTitle, Details.EXPECTED_TITLE);
		}
	}

	static void ManagerHomePage() {

		driver.get(Details.MANAGER_URL);
		String actualTitle = driver.getTitle();
		System.out.println(actualTitle);

	}

	static void changePass() {

		driver.get(Details.MANAGER_URL);
		driver.findElement(By.xpath("/html/body/div[3]/div/ul/li[11]/a")).click();
		driver.findElement(By.name("oldpassword")).sendKeys(Details.INVALID_PASS);
		driver.findElement(By.name("newpassword")).sendKeys("123456");
		driver.findElement(By.name("confirmpassword")).sendKeys("123456");

	}

	static void driverClose() {
		driver.close();
	}

}
