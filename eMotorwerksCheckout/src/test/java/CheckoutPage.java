import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

import static org.junit.Assert.*;

public class CheckoutPage {
    static String stepOneSelector = ".b-shop-checkout__step._n-1";
    static String stepTwoSelector = ".b-shop-checkout__step._n-2";
    static String stepThreeSelector = ".b-shop-checkout__step._n-3";
    static String displayBlockSelector = ".b-shop-checkout__step__body__display";
    static WebDriver driver = eMotorwerksCheckout.driver;

    public static void waitForStepOne() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepOneSelector ) ) ) );
    }

    public static void fillStepOneFields() {
        driver.findElement( By.id( "helper_address_firstname" ) ).sendKeys( "Serhiy" );
        driver.findElement( By.id( "helper_address_lastname" ) ).sendKeys( "Polyukh" );
        driver.findElement( By.id( "helper_address_telephone" ) ).sendKeys( "555-555-5555" );
        driver.findElement( By.id( "helper_register_email" ) ).sendKeys( "geuross@gmail.com" );
        driver.findElement( By.cssSelector( "button[data-i_step='1']" ) ).click();
    }

    public static void waitForDisplayStepOneData() {
        WebDriverWait wait = new WebDriverWait( driver, 5 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepOneSelector + ' ' + displayBlockSelector ) ) ) );
    }

    public static void waitForStepTwo() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector ) ) ) );
    }

    public static void checkCountryAndState() {
        Select countrySelect = new Select( driver.findElement( By.id( "helper_shipping_address_country" ) ) );
        String countryValue = countrySelect.getFirstSelectedOption().getAttribute( "value" );
        assertEquals( countryValue, "country_United_States_of_America_223" );
        System.out.println( "Selected country: " +  countryValue );

        Select stateSelect = new Select( driver.findElement( By.id( "helper_shipping_address_state" ) ) );
        String stateValue = stateSelect.getFirstSelectedOption().getAttribute( "value" );
        assertEquals( stateValue, "state_California_4265" );
        System.out.println( "Selected state: " +  stateValue );
    }

    public static void fillStepTwoFields() {
        driver.findElement( By.id( "helper_shipping_address_street" ) ).sendKeys( "154 W 40th Ave" );
        driver.findElement( By.id( "helper_shipping_address_city" ) ).sendKeys( "San Mateo" );
        driver.findElement( By.id( "helper_shipping_address_post_code" ) ).sendKeys( "94403" );
        driver.findElement( By.cssSelector( "button[data-i_step='2']" ) ).click();
    }

    public static void waitForDisplayStepTwoData() {
        WebDriverWait wait = new WebDriverWait( driver, 5 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "hikashop_checkout_cart_shipping_title" ) ) ) );
        String shippingText = driver.findElement( By.id( "hikashop_checkout_cart_shipping_title" ) ).getText();
        assertEquals( shippingText, "Shipping fee" );
        double shippingValue = Double.parseDouble( driver.findElement( By.className( "hikashop_cart_shipping_value" ) ).getText().replace( "$", "" ) );
        System.out.println( "Shipping fee: " +  shippingValue );
        assertEquals( shippingValue, 0.0, 0 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "hikashop_checkout_cart_tax_title" ) ) ) );
        String taxesText = driver.findElement( By.id( "hikashop_checkout_cart_tax_title" ) ).getText();
        assertEquals( taxesText, "Taxes" );
        double taxesValue = Double.parseDouble( driver.findElement( By.className( "hikashop_cart_tax_value" ) ).getText().replace( "$", "" ) );
        System.out.println( "Taxes: " +  taxesValue );
        assertTrue( taxesValue > 0 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector + ' ' + displayBlockSelector ) ) ) );
    }

    public static void waitForStepThree() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepThreeSelector ) ) ) );
    }

    public static void validateStripeIsChecked() {
        WebElement stripeCheckbox = driver.findElement( By.id( "payment_radio_2_3__emotorwerksstripejs_10" ) );
        assertTrue( stripeCheckbox.isSelected() );
    }

    public static void validateBillingSameAsShippingIsChecked() {
        WebElement billingSameAsShippingCheckbox = driver.findElement( By.id( "helper_b_billing_is_shipping" ) );
        assertTrue( billingSameAsShippingCheckbox.isSelected() );
    }

    public static void fillStripeFields() {
        int nextYear = Calendar.getInstance().get( Calendar.YEAR ) + 1;
        String nextYearString = String.valueOf( nextYear );
        WebDriverWait wait = new WebDriverWait( driver, 10 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "helper_cc_owner" ) ) ) );
        driver.findElement( By.id( "helper_cc_owner" ) ).sendKeys( "Test Test" );

        String originalHandle = driver.getWindowHandle();

        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame4" ) ) );
        driver.findElement( By.name( "cardnumber" ) ).sendKeys( "4111 1111 1111 1111" );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame5" ) ) );
        driver.findElement( By.name( "exp-date" ) ).sendKeys( "12/" + nextYearString.substring( nextYearString.length() - 2 ) );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame6" ) ) );
        driver.findElement( By.name( "cvc" ) ).sendKeys( "111" );

        driver.switchTo().window( originalHandle );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikabtn._place-order" ) ) ) );
        driver.findElement( By.cssSelector( ".hikabtn._place-order" ) ).click();

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikabtn._place-order .fa.fa-gear.fa-spin" ) ) ) );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".b-shop-checkout__overlay .fa.fa-cog.fa-spin" ) ) ) );

        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/cart/checkout/confirm/" ) ) );
    }
}
