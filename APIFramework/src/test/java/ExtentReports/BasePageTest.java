package ExtentReports;

import TDD.APIFramework;
import com.aventstack.extentreports.Status;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

public class BasePageTest



{
    private WebDriver driver;

//    @BeforeClass
//    public void setUp() {
//        driver = getDriver();
//    }



    @Test
    public void baseTest1() {
        ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test1");
        System.out.println("Hey im in test1 test");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 1");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 2");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 3");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test1 4");
    }

    @Test
    public void baseTest2() throws InterruptedException {
        ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test2");
        System.out.println("Hey im in test2 test");
        Thread.sleep(3000);
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test2 1");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test2 2");
    }

    @Test
    public void baseTest3() {
        ExtentTestManager.getTest().log(Status.INFO, "Hellooo started base test3");
        System.out.println("Hey im in test3 test");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test3 1");
        ExtentTestManager.getTest().log(Status.INFO, "Hey im in base test3 2");
    }












}
