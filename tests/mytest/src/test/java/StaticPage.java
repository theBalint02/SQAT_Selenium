import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import static org.junit.Assert.*;


public class StaticPage {
    protected WebDriver driver;
    private String url;
    private String expectedTitleKeyword;
    private By titlediv = By.xpath("/html/body/div[1]/header/div/div/div[1]/a");

    public StaticPage(WebDriver driver, String url, String expectedTitleKeyword) {
        this.driver = driver;
        this.url = url;
        this.expectedTitleKeyword = expectedTitleKeyword;
    }

    public void open() {
        driver.get(url);
    }

    public void verifyTitleContainsKeyword() {
        WebElement title = driver.findElement(titlediv);
        assertTrue(title.getText().toUpperCase().contains(expectedTitleKeyword));
    }
}
