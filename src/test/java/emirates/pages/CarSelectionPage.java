package emirates.pages;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class CarSelectionPage {

    private WebDriver driver;
    private WebDriverWait webDriverWait;

    private By chromeIcon = By.id("buttonStartWebview");
    private By toHomeScreen = By.id("goBack");
    private By enterName = By.xpath("//input[@name='name']");
    private By startAgain = By.xpath("//a[href='here']");
    private By submitrequest = By.xpath("//*[@value='Send me your name!']");

    private By popUpWindow = By.id("showPopupWindowButton");
    private By exceptionTestButton = By.id("exceptionTestButton");

    public CarSelectionPage(WebDriver driver){
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, 5);
    }

    public void homeScreen(){
        waitForElement(chromeIcon);
    }

    public void clickOnPopUps(AndroidDriver androidDriver){
        driver.findElement(popUpWindow).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertTrue(alert.getText().contains("It's a PopupWindow"));
        alert.dismiss();

        driver.findElement(exceptionTestButton).click();
        if (!androidDriver.currentActivity().equals("io.selendroid.testapp")){
            System.out.println("App Crash");
        }

        File crashLog = (File) driver.manage().logs().get("logcat").getAll();
        try {
            Files.write(Paths.get("./fileName.txt"), crashLog.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void typeNameAndSelectCar(AndroidDriver androidDriver){
        driver.findElement(chromeIcon).click();
        waitForElement(toHomeScreen);

        Set<String> contexts = androidDriver.getContextHandles();
        for(String contextName : contexts) {
            if (contextName.contains("WEBVIEW")){
                androidDriver.context(contextName);
            }
        }

        waitForElement(submitrequest);
        androidDriver.findElement(enterName).clear();
        androidDriver.findElement(enterName).sendKeys("TestName");
        new Select(androidDriver.findElement(By.name("car"))).selectByVisibleText("Audi");
        androidDriver.findElement(submitrequest).click();
        waitForElement(startAgain);
        Assert.assertTrue(androidDriver.findElement(By.xpath("//*[contains(@text='\"TestName\"')]")).isDisplayed());
        Assert.assertTrue(androidDriver.findElement(By.xpath("//*[contains(@text='\"Audi\"')]")).isDisplayed());
    }

    private void waitForElement(By locator){
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }





}
