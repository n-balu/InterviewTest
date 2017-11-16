package emirates.Scenarios;

import emirates.pages.CarSelectionPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.net.MalformedURLException;

public class CarSelection extends MyAndroidBase{

    private CarSelectionPage carSelectionPage;

    @BeforeClass
    public void setUp() throws MalformedURLException {
        connectToAppium();
        carSelectionPage = new CarSelectionPage(driver);
    }


//    haroon.rasheed@dnata.com
//    grishma.alex@emirates.com
//    chakravarti.darukmalli@dnata.com

    @Test
    public void testNameAndCarDisplayed(){
        carSelectionPage.homeScreen();
        carSelectionPage.typeNameAndSelectCar(driver);
    }

    @Test
    public void verifyPopupAndException(){
        carSelectionPage.homeScreen();
        carSelectionPage.clickOnPopUps(driver);
    }

    @AfterClass
    public void tearDown(){
        driver.quit();
    }

}
