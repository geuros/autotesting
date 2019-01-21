import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    static String checkoutButtonSelector = ".hikabtn.hikabtn_checkout_next";
    static WebDriver driver = eMotorwerksCheckout.driver;

    public static void waitForCheckoutButton() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( checkoutButtonSelector ) ) ) );
    }

    public static void goToCheckout() {
        driver.findElement( By.cssSelector( checkoutButtonSelector ) ).click();
    }
}
