package com.cybertek;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class PriceDemo {

	public static boolean verifyTotal(String price, String options, String fee, String total) {
		Integer pr = Integer.parseInt(price);
		Integer op = Integer.parseInt(options);
		Integer fe = Integer.parseInt(fee);
		Integer end = pr + op + fe;
		String endTotal = end + "";
		if (endTotal.equals(total)) {
			return true;
		}
		return false;

	}

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		driver.get("https://www.porsche.com/usa/modelstart/");

		driver.findElement(By.partialLinkText("718")).click();
		String text = driver.findElement(By.xpath("//*[@id='m982120']/div[1]/div[2]/div[2]")).getText();
		String basePrice = text.substring(6).substring(0, 7).replace(",", "").trim();
		System.out.println(basePrice);
		String originalPage = driver.getWindowHandle();
		System.out.println(originalPage);

		driver.findElement(By.xpath("//*[@id='m982120']/div[2]/div/a")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		
		String buildPage = driver.getWindowHandle();
		System.out.println(buildPage);
		
		String t2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[1]/div[2]")).getText();
		String basePriceBuild = t2.substring(1, 7).replace(",", "");
		System.out.println(basePriceBuild);

		if (basePrice.equals(basePriceBuild)) {
			System.out.println("Base Prices Match");
		} else {
			System.out.println("Base prices Do Not Match");
		}
		Thread.sleep(1000);
		
		String eqipmentPrice = driver.findElement(By.xpath("//*[@id='s_price']/div[1]/div[2]/div[2]")).getText()
				.substring(1, 2);

		System.out.println("Equipment total is " + eqipmentPrice);

		if (eqipmentPrice.equals("0")) {
			System.out.println("Equipment price is 0");
		} else {
			System.out.println("Equipment price is NOT 0");
		}

		String totalPrice = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		System.out.println("Total price is " + totalPrice);
		String fees = driver.findElement(By.xpath("//*[@id='s_price']/div[1]/div[3]/div[2]")).getText().substring(1, 6)
				.replace(",", "");
		System.out.println("Fees are " + fees);

		if (verifyTotal(basePrice, eqipmentPrice, fees, totalPrice)) {
			System.out.println("Totals Match");
		} else {
			System.out.println("Totals DO NOT match");
		}

		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_FJ5\"]/span")).click();
		String miamiPrice = "2580";
		String updatedOptions = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1, 6).replace(",", "");
		if (miamiPrice.equals(updatedOptions)) {
			System.out.println("Miami Blue price was added to options");
		} else {
			System.out.println("Fail");
		}

		String totalUpdate1 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		System.out.println(totalUpdate1);
		if (verifyTotal(basePrice, updatedOptions, fees, totalUpdate1)) {
			System.out.println("Totals Match After Color Update");
		} else {
			System.out.println("Totals DO NOT Match After Color Update");
		}

		driver.findElement(By.xpath("//*[@id=\"s_exterieur_x_MXRD\"]/span/span")).click();
		String wheelsMiami = "6330";
		String wheelsMiamiOptions = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1, 6).replace(",", "");
		if (wheelsMiami.equals(wheelsMiamiOptions)) {
			System.out.println("Carrera Wheels were added to options");
		} else {
			System.out.println("Fail");
		}

		String totalUpdate2 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (verifyTotal(basePrice, wheelsMiamiOptions, fees, totalUpdate2)) {
			System.out.println("Totals Match After Color And Wheel Update");
		} else {
			System.out.println("Totals DO NOT Match After Color And Wheel Update");
		}
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,1350)", "");
		WebElement seatButton = driver.findElement(By.cssSelector("span[id='s_interieur_x_PP06']"));
		seatButton.click();

		String updateSeats = "8660";
		String seats = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1, 6).replace(",", "");
		if (seats.equals(updateSeats)) {
			System.out.println("14-Way seats were added to options");
		} else {
			System.out.println("Fail");
		}

		String totalUpdate3 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (verifyTotal(basePrice, seats, fees, totalUpdate3)) {
			System.out.println("Totals Match After Seats Update");
		} else {
			System.out.println("Totals DO NOT Match After Seats Update");
		}

		jse.executeScript("window.scrollBy(0,1350)", "");
		driver.findElement(By.xpath("//*[@id=\"IIC_subHdl\"]")).click();
		
		WebElement trim = driver.findElement(By.cssSelector("span[id='vs_table_IIC_x_PEKH_x_c01_PEKH']"));
		trim.click();

		String carbon = "10200";
		String carbonTrim = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (carbon.equals(carbonTrim)) {
			System.out.println("Carbon trim was added to options");
		} else {
			System.out.println("Fail");
		}

		String totalUpdate4 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (verifyTotal(basePrice, carbonTrim, fees, totalUpdate4)) {
			System.out.println("Totals Match After Carbon Trim Update");
		} else {
			System.out.println("Totals DO NOT Match After Carbon Trim Update");
		}

		jse.executeScript("window.scrollBy(0,-250)", "");
		driver.findElement(By.xpath("//*[@id=\"IMG_subHdl\"]")).click();

		WebElement trans = driver.findElement(By.cssSelector("span[id='vs_table_IMG_x_M250_x_c11_M250']"));
		trans.click();
		System.out.println("PDK was added");

		jse.executeScript("window.scrollBy(0,150)", "");
		WebElement brakes = driver.findElement(By.cssSelector("span[id='vs_table_IMG_x_M450_x_c91_M450']"));
		brakes.click();

		String ccbrakes = "20820";
		String cbrakes = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[2]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (ccbrakes.equals(cbrakes)) {
			System.out.println("Ceramic Brakes were added to options");
		} else {
			System.out.println("Fail");
		}

		String totalUpdate5 = driver.findElement(By.xpath("//*[@id=\"s_price\"]/div[1]/div[4]/div[2]")).getText()
				.substring(1, 7).replace(",", "");
		if (verifyTotal(basePrice, cbrakes, fees, totalUpdate5)) {
			System.out.println("Totals Match After Final Build");
		} else {
			System.out.println("Totals DO NOT Match After Final Build");
		}

		// driver.close();
	}

}
