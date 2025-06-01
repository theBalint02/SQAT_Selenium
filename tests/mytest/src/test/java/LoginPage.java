import java.util.Random;

import org.openqa.selenium.*;

public class LoginPage extends BasePage {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static final Random random = new Random();

    private By emailField = By.xpath("//form//input[@type='email' and @id='email']");
    private By passwordField = By.xpath("//form//input[@type='password' and @id='password']");
    private By submitBtn = By.xpath("//button[@class='btn btn-primary']");
    private By failed = By.xpath("/html/body/div/div[2]/div[2]/div/div");

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

    public static String randomEmail(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
        }
        return sb.toString() + "@gmail.com";
    }

    public static String randomPassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i=0; i<length; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }
    
    public Boolean isFailed(){
        WebElement fail = driver.findElement(failed);
        return fail.getText().contains("Hib");
    }
}
