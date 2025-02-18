import com.microsoft.playwright.*;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;


public class LoginPlaywrightTest {

    Playwright playwright;
    Browser browser;
    Page page;
    String overstockUrl = "https://www.overstock.com/";
    public static String testEmail="automationtester-" + UUID.randomUUID() + "@overstock.com";

    @Before
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions().setHeadless(false)
        );
        page = browser.newPage();
    }

    /* www.overstock.com is a publicly facing website. We are taking an example of logging into www.overstock.com
        to show the difference between Selenium and Playwright. Test steps include:

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
    public void loginToOverstock_usingPlaywright() {

        page.navigate(overstockUrl);
        page.locator("//a[@href='/account/login']").click();
        page.waitForSelector("//h1");
        assertThat(page.locator("//h1")).hasText("Sign in or Create an Account");
        page.locator("//input[@id='email']").fill(testEmail);
        page.locator("//button[@type='submit']").click();
        page.waitForSelector("//h1");
        assertThat(page.locator("//h1")).hasText("Create Your Account");
        page.locator("//input[@id='password']").fill("Pass123$");
        page.locator("//button[@type='submit']").click();
        assertThat(page).hasTitle("Explore the New Overstock");
        page.navigate(overstockUrl+"account");
        assertThat(page).hasTitle("Account – Overstock");
        page.locator("//a[@href='/account/logout']").click();
    }
}
