import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class ProfilePage extends BasePage {

    private By profileButton = By.xpath("/html/body/div[1]/header/div/div/div[3]/div[2]/div/a[1]");
    private By hunbasketButton = By.xpath("/html/body/div/header/div/div/div[1]/a");
    private By logoutLink = By.xpath("/html/body/div[1]/header/div/div/div[3]/div[2]/div/a[2]");
    private By nameDiv = By.xpath("/html/body/div/header/div/div/div[3]/div/a/div[2]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    public void clickProfile() {
        WebElement profileBtn = waitForVisible(profileButton);
        clickElement(profileBtn);
    }

    public void clickHunBasket() {
        WebElement hbBtn = waitForVisible(hunbasketButton);
        clickElement(hbBtn);
    }

    public boolean isLogoutVisible() {
        try {
            WebElement logout = waitForVisible(logoutLink);
            return logout.isDisplayed();
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void logout() {
        WebElement logout = waitForVisible(logoutLink);
        clickElement(logout);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(logoutLink));
    }

    public String getNameText() {
        WebElement name = driver.findElement(nameDiv);
        return name.getText();
    }
}
