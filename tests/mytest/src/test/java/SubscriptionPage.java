import org.openqa.selenium.*;

public class SubscriptionPage extends BasePage {

    private By subscriptionTitleDiv = By.xpath("/html/body/div[1]/div[1]/div/div/div/h2");

    public SubscriptionPage(WebDriver driver) {
        super(driver);
    }

    public void open(String url) {
        driver.get(url);
    }

    public String getSubscriptionText() {
        WebElement element = waitForVisible(subscriptionTitleDiv);
        return element.getText();
    }

    public boolean verifySubscriptionText() {
        return getSubscriptionText().contains("fizet");
    }
}
