package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCaseOne {

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
    public void testCaseOne() throws InterruptedException {

        driver.get("https://www.saucedemo.com");
        driver.manage().window().setSize(new Dimension(1055,810));
        Thread.sleep(1000);
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Thread.sleep(1000);

        // Header name check
        String header = driver.findElement(By.cssSelector("#header_container > div.header_secondary_container > span")).getText();
        Assert.assertEquals(header, "PRODUCTS");

        // Shopping cart element at the top right check
        boolean shoppingCart = driver.findElement(By.className("shopping_cart_link")).isDisplayed();
        Assert.assertTrue(shoppingCart);

        // Burger element at the top left side check
        boolean burgerMenu = driver.findElement(By.id("react-burger-menu-btn")).isDisplayed();
        Assert.assertTrue(burgerMenu);

        // Twitter, Facebook, LinkedIn elements (links) check
       String twitterLink = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[1]/a")).getDomAttribute("href");
       Assert.assertEquals(twitterLink, "https://twitter.com/saucelabs");
       String facebookLink = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[2]/a")).getDomAttribute("href");
       Assert.assertEquals(facebookLink, "https://www.facebook.com/saucelabs");
       String linkedinLink = driver.findElement(By.xpath("//*[@id=\"page_wrapper\"]/footer/ul/li[3]/a")).getDomAttribute("href");
       Assert.assertEquals(linkedinLink, "https://www.linkedin.com/company/sauce-labs/");


        // Logout check
        driver.findElement(By.id("react-burger-menu-btn")).click();
        Thread.sleep(500);
        String logout = driver.findElement(By.id("logout_sidebar_link")).getText();
        // System.out.println(logout);
        Assert.assertEquals(logout, "LOGOUT");

    }
}
