package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
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

        // This test method (test class/case) covers every possible incorrect type of login input and checks
        // expected result in that case
        driver.get("https://www.saucedemo.com");
        driver.manage().window().setSize(new Dimension(1055, 810));
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).sendKeys("secret_sauce");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("");
        driver.findElement(By.id("login-button")).click();

        String incorrectUsernamePassword = "Epic sadface: Username and password do not match any user in this service";
        String emptyUsernameAndPassword = "Epic sadface: Username is required";
        String emptyPassword = "Epic sadface: Password is required";
        String emptyUsername = "Epic sadface: Username is required";

        String incorrectLogin = driver.findElement(By.xpath("//*[@id=\"login_button_container\"]/div/form/div[3]/h3")).getText();
        boolean result = incorrectLogin.contains(incorrectUsernamePassword) ||
                         incorrectLogin.contains(emptyUsernameAndPassword) ||
                         incorrectLogin.contains(emptyPassword) || incorrectLogin.contains(emptyUsername);

        Assert.assertTrue(result);

    }
}
