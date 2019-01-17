import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    static String cookieBoxCloseButtonSelector = "#pwebbox204_container .pwebbox_bottombar_toggler";
    static String cartButtonSelector = "#hikashop_cart_module a[href='/cart']";
    static String addToCartButtonSelector = "#hikashop_product_quantity_main .hikacart";

    public static void closeCookieBaner( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( cookieBoxCloseButtonSelector ) ) ) );
        driver.findElement( By.cssSelector( cookieBoxCloseButtonSelector ) ).click();
    }

    public static void addProductToCart( WebDriver driver ) {
        WebElement addToCartButton = driver.findElement( By.cssSelector( addToCartButtonSelector ) );
        Actions actions = new Actions( driver );
        actions.moveToElement( addToCartButton, 0,-100 );
        actions.perform();
        addToCartButton.click();
    }

    public static void waitForSmallCart( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( cartButtonSelector ) ) ) );
    }

    public static void goToCart( WebDriver driver ) {
        driver.findElement( By.cssSelector( cartButtonSelector ) ).click();
    }
}
