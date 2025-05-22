import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class HunBasketTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProfilePage profilePage;

    @Before
    public void setup() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        this.driver = new RemoteWebDriver(new URL("http://selenium:4444/wd/hub"), options);
        this.driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @Test
    public void testHunBasketWebsite() {

        homePage.open();
        assertTrue(homePage.getTitle().contains("HUNBASKET"));

        homePage.closeCookiePopup();
        homePage.clickLogin();

        loginPage.login("telkesbalint02@gmail.com", "teszt123?");

        profilePage.clickProfile();
        assertFalse(driver.getTitle().contains("HUNBASKET"));

        profilePage.clickHunBasket();
        assertTrue(driver.getTitle().contains("HUNBASKET"));

        driver.navigate().back();
        assertFalse(driver.getTitle().contains("HUNBASKET"));

        assertTrue(profilePage.isLogoutVisible());
        profilePage.logout();

        assertFalse(profilePage.isLogoutVisible());

        assertTrue(profilePage.getNameText().contains("Bel"));
        }

    @Test
    public void testMultipleStaticPages() {
        List<StaticPage> pages = Arrays.asList(
            new StaticPage(driver, "https://www.hunbasket.tv/", "HUNBASKET"),
            new StaticPage(driver, "https://www.hunbasket.tv/profil", "HUNBASKET"),
            new StaticPage(driver, "https://www.hunbasket.tv/elofizetesek", "HUNBASKET")
            );

        for (StaticPage page : pages) {
            page.open();
            page.verifyTitleContainsKeyword();
            }
    }

    @After
    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}
