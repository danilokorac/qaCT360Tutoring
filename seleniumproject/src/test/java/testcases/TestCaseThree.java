package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCaseThree {
    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\dkorac\\Downloads\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void testCaseThree() {
        
    }
}
