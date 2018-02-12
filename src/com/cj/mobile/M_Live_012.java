package com.cj.mobile;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import java.net.URL;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.pack.DuplicateMobileLive;
import com.cj.util.SmartProperties;

public class M_Live_012 {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	WebElement element = null;
	boolean setupSuccess = true;
	private String M_URL = null;

	/**
	 * 
	 * @author 조성주 
	 * Date : 2017-11-09  
	 * Subject : CJ Mall 운영  
	 * Name : M_Schedule_012  
	 * Scenario : CJmall > TV 쇼핑 > 편성표 > 상품선택 > 바로구매 > 로그인 > 주문서
	 * Assertion : 주문서 Text 체크
	 *   
	 */

	@Before
	public void setUp() throws Exception {

		SmartProperties sp = SmartProperties.getInstance();
		M_URL = sp.getProperty("M_URL");

		DesiredCapabilities caps = new DesiredCapabilities();
		caps = DesiredCapabilities.android();

		// device name은 큰 의미없음. LG폰도 이 옵션으로 수행됨
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, "LGF460S859d639d");
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
		caps.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");

		URL appiumUrl = new URL("http://127.0.0.1:4723/wd/hub");

		System.out.println("Start driver.");
		driver = new AndroidDriver<WebElement>(appiumUrl, caps);

	}

	@Test
	public void m_schedule_012() throws Exception {
		driver.get(M_URL);
		
		
    	String Single =".//*[@id='scheduleItem']/div[24]/ul/li/a"; // 단일상품
    	String Double =".//*[@id='scheduleItem']/div[24]/ul/li[1]/a"; // 복수상품
		String SingleCon =".//*[@id='scheduleItem']/div[24]/ul/li/a/div[2]/div/span"; // 단일상품상담
		String DoubleCon =".//*[@id='scheduleItem']/div[24]/ul/li[1]/a/div[2]/div[1]/div/span"; // 단일상품상담
		DuplicateMobileLive duplicate = new DuplicateMobileLive(driver);
		duplicate.schedule(Single,Double,SingleCon,DoubleCon);
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
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
