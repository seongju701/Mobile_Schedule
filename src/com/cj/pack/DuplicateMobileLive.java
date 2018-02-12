package com.cj.pack;

import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.cj.pack.DuplicateMobileLive;
import com.cj.util.SmartProperties;


public class DuplicateMobileLive{
	private WebDriver driver;
	private String ID_1 = null;
	private String PW_1 = null;
	boolean isExist1 = false;
	boolean isExist2 = false;
	boolean isExist3 = false;
	boolean isExist4 = false;
	boolean isExist5 = false;
	boolean isExist6 = false;
	boolean isExist7 = false;
	boolean isExist8 = false;
	boolean isExist9 = false;
	boolean isExist10 = false;
	boolean isExist11 = false;
	boolean isExist12 = false;
//그냥 생성자
  public DuplicateMobileLive(WebDriver Driver){
		driver = Driver;
		SmartProperties sp = SmartProperties.getInstance();
		ID_1 = sp.getProperty("ID_1");
		PW_1 = sp.getProperty("PW_1");
  }

	public void schedule(String Single,String Double,String SingleCon,String DoubleCon) throws InterruptedException {
		
		
		// 알럿 발생확인
		// 오늘 하루 보지 않기 버튼 클릭
		driver.findElement(By.xpath(".//*[@id='notToday']/label")).click();
		System.out.println("오늘 하루 보지 않기 버튼 클릭");
		Thread.sleep(3000);

		// TV쇼핑
		driver.findElement(By.linkText("TV쇼핑")).click();
		System.out.println("TV쇼핑 진입");
		Thread.sleep(3000);

		// 편성표 보기 버튼 클릭

		isExist11 = existElement(driver, By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[3]/ul/li[2]/a"), "편성표 진입");

		if (isExist11 && true) {
			driver.findElement(By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[3]/ul/li[2]/a")).click();
			System.out.println("편성표 보기 클릭");
			Thread.sleep(3000);
		} else {
			driver.findElement(By.xpath("//*[@id=\"moduleArea\"]/div[1]/div[2]/ul/li[2]/a")).click();
			System.out.println("편성표 보기 클릭");
			Thread.sleep(3000);
		}

		
		WebElement searchBtn2 = driver.findElement(By.xpath("//*[@id=\"container\"]"));
        Actions action2 = new Actions(driver);
        action2.moveToElement(searchBtn2).build().perform();
		Thread.sleep(3000);

		// n번째 상품 선택
		// 해당 상품이 편성표에 존재하는지 체크
		isExist2 = existElement(driver, By.xpath(Single), "1번째 단일상품");
		isExist3 = existElement(driver, By.xpath(Double), "1번째 복수상품");
		;
		if (isExist2 && true) {
			System.out.println("편성표 존재 합니다. (단일상품) 계속 진행합니다.");
			WebElement searchBtn1 = driver.findElement(By.xpath(Single));
			Actions action1 = new Actions(driver);
			action1.moveToElement(searchBtn1).perform();
			Thread.sleep(3000);
		} else {
			if (isExist3 && true) {
				System.out.println("편성표 존재 합니다. (복수상품) 계속 진행합니다.");
				WebElement searchBtn1 = driver.findElement(By.xpath(Double));
				Actions action1 = new Actions(driver);
				action1.moveToElement(searchBtn1).perform();
				Thread.sleep(3000);
			} else {
				System.out.println("더이상 상품이 존재하지 않습니다. 여기서 종료 합니다.");
				assertTrue(true);
				return;
			}
		}

		// 상담신청 체크 (단일상품)
		// 대표상품 가격란에 '상담신청상품' 체크
		isExist4 = existElement(driver, By.xpath(SingleCon),
				"단일 상담신청 상품");
		isExist5 = existElement(driver, By.xpath(DoubleCon),
				"복수 상담신청 상품");
		if (isExist4 && driver.findElement(By.xpath(SingleCon))
				.getText().equals("상담신청 상품")||isExist4 && driver.findElement(By.xpath(SingleCon))
				.getText().equals("월 렌탈료")) {
			System.out.println("상담신청상품 입니다. (단일)");
			assertTrue(true);
			return;
		} else {
			// 상담신청 체크 (복수상품)
			// 대표상품 가격란에 '상담신청상품' 체크
			if (isExist5
					&& driver.findElement(By.xpath(DoubleCon))
							.getText().equals("상담신청 상품")||isExist5
					&& driver.findElement(By.xpath(DoubleCon))
							.getText().equals("월 렌탈료 ")) {
				System.out.println("상담신청상품 입니다. (복수)");
				assertTrue(true);
				return;
			}
			// 상담신청 상품이 아닐경우 else 진행
			else {
				System.out.println("상담신청상품 아닙니다. 계속 진행");
				Thread.sleep(3000);
			}
		}

		// 대표상품 선택
		isExist6 = existElement(driver, By.xpath(Single), "상품진입");
		if (isExist6) {
			// 단일상품일경우 (보통 이 옵션으로 모두 선택됨)
			driver.findElement(By.xpath(Single)).click();
			System.out.println("대표상품 선택 (단일상품)");
			Thread.sleep(3000);
		} else {
			// 복수상품일경우
			driver.findElement(By.xpath(Double)).click();
			System.out.println("대표상품 선택 (복수상품)");
			Thread.sleep(3000);
		}

		// 주문가능여부 체크
		isExist7 = existElement(driver, By.xpath(".//*[@id='orderArea']/div[1]/a"), "주문가능여부 체크");
		// 매진상태 체크
		if (isExist7 && driver.findElement(By.xpath(".//*[@id='orderArea']/div[1]/a")).getText().equals("매진")) {
			System.out.println("매진된 상품입니다");
			assertTrue(true);
			return;
		} else {
			// 주문 가능한 상태가 아닐 경우 체크
			if (isExist7
					&& driver.findElement(By.xpath(".//*[@id='orderArea']/div[1]/a")).getText().equals("생방송 중 주문가능")) {
				System.out.println("주문 가능한 상태가 아닙니다");
				assertTrue(true);
				return;
			}
			// 주문가능할 경우 아래 내용 출력
			else {
				if (isExist7 && driver.findElement(By.xpath(".//*[@id='orderArea']/div[1]/a")).getText()
						.equals("생방송 중 주문가능")) {
					System.out.println("생방송 중 주문가능 상품 입니다.");
					assertTrue(true);
					return;
				} else {
					System.out.println("매진 상품 아님. 계속 진행");
					Thread.sleep(3000);
				}
			}
		}

		// 구매하기 버튼 클릭
		driver.findElement(By.linkText("구매하기")).click();
		System.out.println("구매하기 버튼 클릭");
		Thread.sleep(3000);

		isExist8 = existElement(driver, By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/span"),
				"옵션버튼 체크");
		isExist9 = existElement(driver,
				By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/div[2]/div[1]/ul/li[1]/a/span[3]"),
				"옵션 매진 상태 체크");
		isExist12 = existElement(driver,
				By.xpath("//*[@id=\"orderArea\"]/div[2]/div[1]/div/div/div[1]/div[2]/span"),
				"옵션버튼2 체크");
		// 옵션버튼이 있는경우
		if (isExist8) {
			System.out.println("옵션이 존재 합니다.");
			Thread.sleep(1000);

			driver.findElement(By.xpath(".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/span")).click();
			System.out.println("옵션 버튼 클릭");
			Thread.sleep(3000);
			driver.findElement(By.xpath("//*[@id=\"orderArea\"]/div[2]/div[1]/div/div/div[1]/div[1]/div[2]/div[1]/ul/li[1]")).click();
			System.out.println("옵션 상세 클릭");
			
			// 옵션 매진 체크
			if (isExist9 && driver
					.findElement(By.xpath(
							".//*[@id='orderArea']/div[2]/div[1]/div/div/div[1]/div/div[2]/div[1]/ul/li[1]/a/span[3]"))
					.getText().equals("매진")) {
				System.out.println("해당 옵션 매진 입니다. 여기서 종료 합니다.");
				assertTrue(true);
				return;
			} else {

				if (isExist12) {
					System.out.println("옵션2가 존재 합니다.");
					Thread.sleep(1000);

					driver.findElement(By.xpath("//*[@id=\"orderArea\"]/div[2]/div[1]/div/div/div[1]/div[2]/span"))
							.click();
					System.out.println("옵션2 버튼 클릭");
					driver.findElement(
							By.xpath("//*[@id=\"orderArea\"]/div[2]/div[1]/div/div/div[1]/div[2]/div[2]/div/ul/li[1]"))
							.click();
					System.out.println("옵션상세 버튼 클릭");
				}
				Thread.sleep(3000);
				driver.findElement(By.linkText("바로구매")).click();
				System.out.println("바로구매 버튼 선택");
				Thread.sleep(3000);

			}
		}
		// 옵션버튼이 없는경우
		else {
			// 주문 가능한 상태 아닌 경우
			isExist10 = existElement(driver, By.xpath(".//*[@id='orderArea']/div[2]/div[2]/div[2]/a"),
					"주문 가능한 상태가 아닙니다");
			if (isExist10 && true) {
				System.out.println("주문 가능한 상태가 아닙니다.");
				assertTrue(true);
				return;
			} else {
				System.out.println("옵션이 존재 하지 않습니다.");
				Thread.sleep(1000);
				driver.findElement(By.linkText("바로구매")).click();
				System.out.println("바로구매 버튼 선택");
				Thread.sleep(3000);
			}
		}

		Thread.sleep(5000);
		driver.findElement(By.xpath(".//*[@id='id_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='id_input']")).sendKeys(ID_1);
		driver.findElement(By.xpath(".//*[@id='password_input']")).clear();
		driver.findElement(By.xpath(".//*[@id='password_input']")).sendKeys(PW_1);

		// 가상키보드를 끄기위해 빈공간 클릭
		driver.findElement(By.xpath(".//*[@id='content']/div[1]/div[2]/fieldset/div[2]")).click();

		// 로그인 버튼 클릭
		driver.findElement(By.xpath(".//*[@id='loginSubmit']")).click();
		System.out.println("로그인 성공");

		Thread.sleep(7000);

		// 주문서 진입 확인
		if ("주문서".equals(driver.findElement(By.xpath(".//*[@id='header']/div/h1")).getText())) {
			System.out.println("주문서 확인 종료 합니다.");
			Thread.sleep(3000);
			assertTrue(true);
			return;
		} else {
			System.out.println("주문서 진입 안됨");
			Thread.sleep(3000);
			assertTrue(false);
			return;
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