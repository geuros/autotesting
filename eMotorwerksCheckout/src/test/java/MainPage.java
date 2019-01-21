import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    static String cookieBoxCloseButtonSelector = "#pwebbox204_container .pwebbox_bottombar_toggler";
    static String storeMenuItemSelector = ".b-header__menu .item-1038";
    static String HomeEvChargingStationsMenuItemSelector = ".b-header__menu .item-1133";

    public static void closeCookieBaner( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( cookieBoxCloseButtonSelector ) ) ) );
        driver.findElement( By.cssSelector( cookieBoxCloseButtonSelector ) ).click();
    }

    public static void navigateToCategoryPage( WebDriver driver ) {
        WebElement storeMenuItem = driver.findElement( By.cssSelector( storeMenuItemSelector ) );
        Actions action = new Actions( driver );
        action.moveToElement( storeMenuItem ).build().perform();

        WebDriverWait wait = new WebDriverWait( driver, 1 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( storeMenuItemSelector + " > ul.dropdown-menu" ) ) ) );

        driver.findElement( By.cssSelector( HomeEvChargingStationsMenuItemSelector ) ).click();

        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/store/residential" ) ) );
    }
}