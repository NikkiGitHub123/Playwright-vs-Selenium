import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.UUID;

public class LoginSeleniumTest {

    public static WebDriver driver;
    String overstockUrl = "https://www.overstock.com/";
    public static String testEmail="automationtester-" + UUID.randomUUID() + "@overstock.com";
    WebDriverWait wait;

    @Before
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        final WebDriver.Timeouts implicitlyWait = driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }


    /* www.overstock.com is a publicly facing website. We are taking an example of logging into www.overstock.com
        to show the difference between Test frameworks Selenium and Playwright. Test steps include:

        Navigate to Overstock homepage
        Click SignIn button
        Verify Sign In page header
        Enter email
        Click Continue button
        Verify Password page Header
        Enter password
        Click SignIn
        Verify page Header "Account"
        Click Logout
 */
    @Test
    public void loginToOverstock_usingSelenium() {

        driver.get(overstockUrl);
        driver.findElement(By.xpath("//a[@href='/account/login']")).click();
        String signInPageHeaderText = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(signInPageHeaderText, "Sign in or Create an Account");
        driver.findElement(By.id("email")).sendKeys(testEmail);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        String passwordPageHeaderText = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(passwordPageHeaderText, "Create Your Account");
        driver.findElement(By.id("password")).sendKeys("Password123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleIs("Explore the New Overstock"));
        driver.get(overstockUrl+"account");
        String accountPageHeader = driver.findElement(By.xpath("//h1")).getText();
        Assert.assertEquals(accountPageHeader, "Account – Overstock");
        driver.findElement(By.xpath("//a[@href='/account/logout']")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}