package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestCaseTwo {
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
    public void testCaseTwo() /*throws InterruptedException*/ {

        // Browser setup and login on website
        driver.get("https://www.saucedemo.com");
        driver.manage().window().setSize(new Dimension(1055, 810));
        //Thread.sleep(1000);
        driver.findElement(By.id("user-name")).click();
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Verification of title, description and price on given item
        driver.findElement(By.cssSelector("#item_4_title_link > div")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")));
        String title = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[1]")).getText();
        String description = driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[2]")).getText();
        float price = Float.parseFloat(driver.findElement(By.xpath("//*[@id=\"inventory_item_container\"]/div/div/div[2]/div[3]")).getText().substring(1));

        // Title, description and price check
        Assert.assertEquals(title, "Sauce Labs Backpack");
        Assert.assertEquals(description, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");
        // (expected - actual) > 0.01 -> Error!
        Assert.assertEquals(price, 29.99, 0.01);

        // Adding product to cart and proceeding to checkout
        driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.cssSelector("#back-to-products")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"item_5_title_link\"]/div")));
        driver.findElement(By.xpath("//*[@id=\"add-to-cart-sauce-labs-fleece-jacket\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"shopping_cart_container\"]/a")));
        driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"checkout\"]")));
        driver.findElement(By.xpath("//*[@id=\"checkout\"]")).click();

        // Order checkout information input
        driver.findElement(By.id("first-name")).click();
        driver.findElement(By.id("first-name")).sendKeys("Danilo");
        driver.findElement(By.id("last-name")).click();
        driver.findElement(By.id("last-name")).sendKeys("Korac");
        driver.findElement(By.id("postal-code")).click();
        driver.findElement(By.id("postal-code")).sendKeys("36000");

        // Finishing order and verifying that required text is displayed
        driver.findElement(By.xpath("//*[@id=\"continue\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"finish\"]")));
        driver.findElement(By.xpath("//*[@id=\"finish\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("complete-header")));
        String completedOrder = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(completedOrder, "THANK YOU FOR YOUR ORDER");

        // Logout
        driver.findElement(By.id("react-burger-menu-btn")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
        driver.findElement(By.id("logout_sidebar_link")).click();

    }


}
