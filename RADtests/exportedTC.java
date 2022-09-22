package com.example.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ExportedTC {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "https://qa.velocitypayment.com/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testExportedTC() throws Exception {
    driver.get(baseUrl + "/client/rad/index.html");
    new Select(driver.findElement(By.id("taxType"))).selectByVisibleText("Estimated Tax");
    // ERROR: Caught exception [ERROR: Unsupported command [selectFrame | tx_iframe_TokenXPSSNIFrame | ]]
    driver.findElement(By.id("data")).clear();
    driver.findElement(By.id("data")).sendKeys("123-45-1234");
    // ERROR: Caught exception [ERROR: Unsupported command [selectWindow | name=tx_iframe_TokenXReEnterPSSNIFrame | ]]
    driver.findElement(By.id("data")).clear();
    driver.findElement(By.id("data")).sendKeys("123-45-1234");
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
