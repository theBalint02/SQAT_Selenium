import org.openqa.selenium.*;

public class HomePage extends BasePage {

    private By cookieCloseBtn = By.xpath("//*[@id=\"cookiescript_close\"]");
    private By loginBtn = By.xpath("//a[@class='btn-profile']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.hunbasket.tv/");
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public void closeCookiePopup() {
        WebElement cookieCloseButton = waitForClickable(cookieCloseBtn);
        clickElement(cookieCloseButton);
    }

    public void clickLogin() {
        WebElement loginButton = waitForClickable(loginBtn);
        clickElement(loginButton);
    }
}
