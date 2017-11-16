package emirates.Scenarios;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class MyAndroidBase {

    public static AndroidDriver driver;
    public DesiredCapabilities capabilities = new DesiredCapabilities();

    public MyAndroidBase(){

        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appium-version", "1.7");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1.1");
        capabilities.setCapability("deviceName", "Mydevice");
        capabilities.setCapability("app", "C:\\Users\\Admin\\Downloads\\selendroid-test-app-0.17.0.apk");
        capabilities.setCapability("appPackage", "io.selendroid.testapp");
    }

    public void connectToAppium() throws MalformedURLException{
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub") , capabilities);
    }
}
