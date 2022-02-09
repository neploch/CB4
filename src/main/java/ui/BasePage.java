package ui;

import com.google.common.util.concurrent.Uninterruptibles;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class BasePage {

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions action;
    public WebElement webElement;
    public final int globalDelay = 30;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, globalDelay);
    }

    public void clickVisible(By element){
        wait.until(ExpectedConditions.elementToBeClickable(element));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
    }

    public void clickVisible(String xpath){
        clickVisible(By.xpath(xpath));
    }

    public boolean waitForElementToBeVisible(By element) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        }catch (Throwable t){
            return false;
        }
    }

    public boolean waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        return true;
    }

    public boolean waitForElementToBeVisible(By element, int delay) {
        wait = new WebDriverWait(driver, delay);
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
            return true;
        }catch (Throwable t) {
            return false;
        }finally {
            wait = new WebDriverWait(driver, globalDelay);
        }
    }

    public boolean waitForElementToBeVisible(String xpath, int delay) {
        return waitForElementToBeVisible(By.xpath(xpath),delay);
    }


    public boolean waitForElementPresence(By element){
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
        return true;
    }

    public void sendTextToVisibleElement(By element, String text) {
        waitForElementToBeVisible(element);
        driver.findElement(element).clear();
        driver.findElement(element).sendKeys(text);
    }

    public String getVisibleElementText(By element){
        waitForElementToBeVisible(element);
        wait(50);
        return driver.findElement(element).getText();
    }

    public String getVisibleElementText(String xpath){
        return getVisibleElementText(By.xpath(xpath));
    }

    public void hoverOverElement(By element){
        action = new Actions(driver);
        webElement = driver.findElement(element);
        action.moveToElement(webElement).build().perform();
    }

    public void hoverOverElement(String xpath){
        hoverOverElement(By.xpath(xpath));
    }

    public void wait(int delay){
        Uninterruptibles.sleepUninterruptibly((long) delay, TimeUnit.MILLISECONDS);
    }

    public void scrollElementIntoView(By element){
        waitForElementPresence(element);
        webElement = driver.findElement(element);
        scrollElementIntoView(webElement);
    }

    public void scrollElementIntoView(WebElement element){
        waitForElementToBeVisible(element);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        wait(300);
    }

    public void hoverOverVisibleElement(By element, int delay){
        waitForElementToBeVisible(element,delay);
        scrollElementIntoView(element);
        hoverOverElement(element);
    }

    public void scrollX(int x){
        JavascriptExecutor jsx = (JavascriptExecutor)driver;
        String script = stringFormat("window.scrollBy(0,%d)", x);
        jsx.executeScript(script, "");
    }

    public boolean waitForAttributeToContain(By element, String attribute, String expectedValue, int delay){
        wait = new WebDriverWait(driver, delay);
        try {
            wait.until(ExpectedConditions.attributeContains(element, attribute, expectedValue));
            return true;
        }catch (Throwable t){
            return false;
        }finally {
            wait = new WebDriverWait(driver, globalDelay);
        }
    }

    public boolean waitForAttributeToContain(String xpath, String attribute, String expectedValue, int delay){
        return waitForAttributeToContain(By.xpath(xpath),attribute,expectedValue, delay);
    }

    public String stringFormat(String template, Integer parameter){
        return String.format(template,parameter);
    }

    public void jsClick(By locator){
        WebElement element = driver.findElement(locator);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public void switchToIframe(By locator){
        waitForElementPresence(locator);
        wait(100);
        driver.switchTo().frame(driver.findElement(locator));
    }

    public void switchToIframe(String xpath){
        switchToIframe(By.xpath(xpath));
    }

}
