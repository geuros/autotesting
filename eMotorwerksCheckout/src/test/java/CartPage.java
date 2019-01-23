import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    static String checkoutButtonSelector = ".hikabtn.hikabtn_checkout_next";
    static String cartRowSelector = ".hikashop_checkout_cart .row0";
    static WebDriver driver = eMotorwerksCheckout.driver;

    public static void waitForCheckoutButton() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( checkoutButtonSelector ) ) ) );
    }

    public static void getData() {
        eMotorwerksCheckout.productName = driver.findElement( By.cssSelector( cartRowSelector + " .hikashop_cart_product_name a" ) ).getText();
        eMotorwerksCheckout.productDescription = driver.findElement( By.cssSelector( cartRowSelector + " .hikashop_cart_product_custom_item_fields" ) ).getText();
        eMotorwerksCheckout.productPrice = Double.parseDouble( driver.findElement( By.cssSelector( cartRowSelector + " .hikashop_product_price_full .hikashop_product_price" ) ).getText().replace( "$", "" ) );
        eMotorwerksCheckout.productQuantity = Integer.parseInt( driver.findElement( By.cssSelector( cartRowSelector + " .hikashop_product_quantity_field" ) ).getAttribute( "value" ) );
    }

    public static void goToCheckout() {
        driver.findElement( By.cssSelector( checkoutButtonSelector ) ).click();
        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/cart/checkout/show/cid-2" ) ) );
        System.out.println( "We on page Checkout" );
    }
}
