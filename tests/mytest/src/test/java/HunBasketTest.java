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
    private SubscriptionPage subscriptionPage;

    private ConfigLoader config;

    @Before
    public void setup() throws MalformedURLException {
        config = new ConfigLoader();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        this.driver = new RemoteWebDriver(new URL(config.getWebDriverRemoteUrl()), options);
        this.driver.manage().window().maximize();

        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        subscriptionPage = new SubscriptionPage(driver);
    }

    @Test
    public void testHunBasketWebsite() {

        homePage.open(config.getBaseUrl());
        assertTrue(homePage.getTitle().contains("HUNBASKET"));

        homePage.closeCookiePopup();
        homePage.clickLogin();

        loginPage.login(loginPage.randomEmail(10), loginPage.randomPassword(10));
        assertTrue(loginPage.isFailed());

        loginPage.login(config.getValidEmail(), config.getValidPassword());

        profilePage.clickProfile();
        assertFalse(driver.getTitle().contains("HUNBASKET"));

        profilePage.sendForm();
        assertTrue(profilePage.isSuccess());

        profilePage.clickHunBasket();
        assertTrue(driver.getTitle().contains("HUNBASKET"));

        driver.navigate().back();
        assertFalse(driver.getTitle().contains("HUNBASKET"));

        assertTrue(profilePage.isLogoutVisible());
        profilePage.logout();

        assertFalse(profilePage.isLogoutVisible());
        assertTrue(profilePage.getNameText().contains("Bel"));

        subscriptionPage.open(config.getBaseUrl()+ "elofizetesek");
        assertTrue(subscriptionPage.verifySubscriptionText());
    }

    @Test
    public void testMultipleStaticPages() {
        List<StaticPage> pages = Arrays.asList(
            new StaticPage(driver, config.getBaseUrl(), "HUNBASKET"),
            new StaticPage(driver, config.getBaseUrl() + "profil", "HUNBASKET"),
            new StaticPage(driver, config.getBaseUrl() + "elofizetesek", "HUNBASKET")
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
