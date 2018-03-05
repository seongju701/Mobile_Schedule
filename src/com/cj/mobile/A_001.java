package tests;
import io.appium.java_client.android.AndroidDriver;

import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.util.SmartProperties;

import org.junit.*;
import org.openqa.selenium.*;

public class A_001 {
	private WebDriver driver;
	String devices = null;
	@Before
	public void setUp() throws Exception {
		SmartProperties sp = SmartProperties.getInstance();
		devices = sp.getProperty("devices");
		System.out.println(devices);
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("deviceName", devices);
		caps.setCapability("udid", devices); // 핸드폰 adb devices 값 입력
		caps.setCapability("platformName", "Android");
		caps.setCapability("platformVersion", "6.0");
		caps.setCapability("appPackage", "com.cjoshppingphone"); 
		caps.setCapability("appActivity", "com.cjoshppingphone.cjmall.init.activity.Init");
		caps.setCapability("noReset", "true");
		URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");
		System.out.println("Start driver.");
		driver = new AndroidDriver<WebElement>(appiumUrl, caps);
		//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void m_001() throws Exception {
		Thread.sleep(3000);

		boolean isExist = false;
		// 팝업닫기
		isExist = existElement(driver, By.id("com.cjoshppingphone:id/allday_close"), "오늘 하루 보지 않기");
		System.out.println(isExist);
		if (isExist) {
			driver.findElement(By.id("com.cjoshppingphone:id/allday_close")).click();
			System.out.println("닫기버튼 클릭");
		} else {
			System.out.println("팝업 없음");
		}
		System.out.println("팝업닫기");
		Thread.sleep(3000);
		
		driver.findElement(By.id("com.cjoshppingphone:id/main_navigation_btn")).click();
		//좌측카테고리 버튼 클릭
		Thread.sleep(7000);
		System.out.println("좌측카테고리 버튼 클릭");
		//로그인 텍스트 확인
		System.out.println(driver.findElement(By.id("com.cjoshppingphone:id/login_btn")).getText());
		if ("로그인".equals(driver.findElement(By.id("com.cjoshppingphone:id/login_btn")).getText())) {
            System.out.println("TC_1 PASS");
            Thread.sleep(3000);
            assertTrue(true);
            return;
         } else {
            System.out.println("TC_1 FAIL");
            assertTrue(false);
			}
		
		
	}
	@After
	public void tearDown() throws Exception {
		driver.quit();
	}
	public boolean existElement(WebDriver wd, By by, String meaning) {
		WebDriverWait wait = new WebDriverWait(wd, 2);
		// wait.ignoring(NoSuchElementException.class);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(by));

		} catch (TimeoutException e) {

			System.out.println("[" + meaning + "] WebElement does not Exist. time out ");
			return false;
		}
		System.out.println("[" + meaning + "] WebElement Exist.");
		return true;
	}
}
