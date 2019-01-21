import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResidentialPage {
    static String addToCartButtonSelector = ".hikashop_products a[data-addtocart='1758']";
    static String cartButtonSelector = "#hikashop_cart_module a[href='/cart']";

    public static void addProductToCart( WebDriver driver ) {
        driver.findElement( By.cssSelector( addToCartButtonSelector ) ).click();
    }

    public static void waitingForMiniCart( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( cartButtonSelector ) ) ) );
    }

    public static void goToMiniCart( WebDriver driver ) {
        driver.findElement( By.cssSelector( cartButtonSelector ) ).click();

        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/cart" ) ) );
    }
}