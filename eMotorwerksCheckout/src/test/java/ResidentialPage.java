import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResidentialPage {
    static String addToCartButtonSelector = ".hikashop_products a[data-addtocart='1758']";
    static String cartButtonSelector = "#hikashop_cart_module a[href='/cart']";
    static WebDriver driver = eMotorwerksCheckout.driver;

    public static void addProductToCart() {
        driver.findElement( By.cssSelector( addToCartButtonSelector ) ).click();
        System.out.println( "Product added" );
    }

    public static void waitingForMiniCart() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( cartButtonSelector ) ) ) );
        System.out.println( "Mini cart is visible" );
    }

    public static void goToMiniCart() {
        driver.findElement( By.cssSelector( cartButtonSelector ) ).click();
        System.out.println( "Button view cart is visible" );

        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/cart" ) ) );
        System.out.println( "We on page View Cart" );
    }
}