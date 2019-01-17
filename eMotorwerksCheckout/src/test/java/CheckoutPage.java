import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;
import java.util.Date;

public class CheckoutPage {

    static String stepOneSelector = ".b-shop-checkout__step._n-1";
    static String stepTwoSelector = ".b-shop-checkout__step._n-2";
    static String stepThreeSelector = ".b-shop-checkout__step._n-3";

    public static void waitForStepOne( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepOneSelector ) ) ) );
    }

    public static void fillStepOneFields( WebDriver driver ) {
        driver.findElement( By.id( "helper_address_firstname" ) ).sendKeys( "Serhiy" );
        driver.findElement( By.id( "helper_address_lastname" ) ).sendKeys( "Polyukh" );
        driver.findElement( By.id( "helper_address_telephone" ) ).sendKeys( "555-555-5555" );
        driver.findElement( By.id( "helper_register_email" ) ).sendKeys( "geuross@gmail.com" );
        driver.findElement( By.cssSelector( "button[data-i_step='1']" ) ).click();
    }

    public static void waitForStepTwo( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector ) ) ) );
    }

    public static void fillStepTwoFields( WebDriver driver ) {
        driver.findElement( By.id( "helper_shipping_address_street" ) ).sendKeys( "13th, Avenue" );
        driver.findElement( By.id( "helper_shipping_address_city" ) ).sendKeys( "California" );
        driver.findElement( By.cssSelector( "#helper_shipping_address_state option[value='state_California_4265']" ) ).click();
        driver.findElement( By.id( "helper_shipping_address_post_code" ) ).sendKeys( "90623" );
        driver.findElement( By.cssSelector( "#helper_shipping_address_country option[value='country_United_States_of_America_223']" ) ).click();
        driver.findElement( By.cssSelector( "button[data-i_step='2']" ) ).click();
    }

    public static void waitForStepThree( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepThreeSelector ) ) ) );
    }

    public static void checkStripe( WebDriver driver ) {
        driver.findElement( By.cssSelector( "label[for='payment_radio_2_3__emotorwerksstripejs_10']" ) ).click();
    }

    public static void waitForStripe( WebDriver driver ) {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikashop_checkout_payment_card" ) ) ) );
    }

    public static void fillStripeFields( WebDriver driver ) {
        int nextYear = Calendar.getInstance().get( Calendar.YEAR ) + 1;

        driver.findElement( By.id( "helper_cc_owner" ) ).sendKeys( "Test Test" );

        String originalHandle = driver.getWindowHandle();

        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame4" ) ) );
        driver.findElement( By.name( "cardnumber" ) ).sendKeys( "4111 1111 1111 1111" );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame5" ) ) );
        driver.findElement( By.name( "exp-date" ) ).sendKeys( "12/" + nextYear );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame6" ) ) );
        driver.findElement( By.name( "cvc" ) ).sendKeys( "111" );

        driver.switchTo().window( originalHandle );
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikabtn._place-order" ) ) ) );
        driver.findElement( By.cssSelector( ".hikabtn._place-order" ) ).click();
    }
}
