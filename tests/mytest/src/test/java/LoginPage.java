import org.openqa.selenium.*;

public class LoginPage extends BasePage {

    private By emailField = By.xpath("//form//input[@type='email' and @id='email']");
    private By passwordField = By.xpath("//form//input[@type='password' and @id='password']");
    private By submitBtn = By.xpath("//button[@class='btn btn-primary']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void login(String email, String password) {
        WebElement emailInput = waitForVisible(emailField);
        emailInput.sendKeys(email);

        WebElement passwordInput = waitForVisible(passwordField);
        passwordInput.sendKeys(password);

        WebElement submitButton = waitForClickable(submitBtn);
        clickElement(submitButton);
    }
}
