import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Calendar;

import static org.junit.Assert.*;

public class CheckoutPage {
    private static String stepOneSelector = ".b-shop-checkout__step._n-1";
    private static String stepTwoSelector = ".b-shop-checkout__step._n-2";
    private static String stepThreeSelector = ".b-shop-checkout__step._n-3";
    private static String checkoutCartSelector = ".b-shop-checkout__step._n-5";
    private static String displayBlockSelector = ".b-shop-checkout__step__body__display";
    private static WebDriver driver = eMotorwerksCheckout.driver;

    public static void waitForStepOne() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepOneSelector ) ) ) );
        System.out.println( "Checkout Step 1 is active" );
    }

    public static void fillStepOneFields() {
        driver.findElement( By.id( "helper_address_firstname" ) ).sendKeys( eMotorwerksCheckout.clientFirstName );
        driver.findElement( By.id( "helper_address_lastname" ) ).sendKeys( eMotorwerksCheckout.clientSecondName );
        driver.findElement( By.id( "helper_address_telephone" ) ).sendKeys( eMotorwerksCheckout.clientPhoneNumber );
        driver.findElement( By.id( "helper_register_email" ) ).sendKeys( eMotorwerksCheckout.clientEmail );
        driver.findElement( By.cssSelector( "button[data-i_step='1']" ) ).click();
    }

    public static void waitForDisplayStepOneData() {
        WebDriverWait wait = new WebDriverWait( driver, 5 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepOneSelector + ' ' + displayBlockSelector ) ) ) );
        System.out.println( "Checkout Step 1 Data printed" );
    }

    public static void waitForStepTwo() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector ) ) ) );
        System.out.println( "Checkout Step 2 is active" );
    }

    public static void checkCountryAndState() {
        Select countrySelect = new Select( driver.findElement( By.id( "helper_shipping_address_country" ) ) );
        String countryValue = countrySelect.getFirstSelectedOption().getAttribute( "value" );
        assertEquals( countryValue, eMotorwerksCheckout.correctCountryValue );
        System.out.println( "Selected country: " +  countryValue );

        Select stateSelect = new Select( driver.findElement( By.id( "helper_shipping_address_state" ) ) );
        String stateValue = stateSelect.getFirstSelectedOption().getAttribute( "value" );
        assertEquals( stateValue, eMotorwerksCheckout.correctStateValue );
        System.out.println( "Selected state: " +  stateValue );
    }

    public static void fillStepTwoFields() {
        driver.findElement( By.id( "helper_shipping_address_street" ) ).sendKeys( eMotorwerksCheckout.clientAddress );
        driver.findElement( By.id( "helper_shipping_address_city" ) ).sendKeys( eMotorwerksCheckout.clientCity );
        driver.findElement( By.id( "helper_shipping_address_post_code" ) ).sendKeys( eMotorwerksCheckout.clientZipCode );
        driver.findElement( By.cssSelector( "button[data-i_step='2']" ) ).click();
    }

    public static void waitForDisplayStepTwoData() {
        WebDriverWait wait = new WebDriverWait( driver, 5 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector + " .hikashop_checkout_loading_spinner .fa-spin" ) ) ) );
        System.out.println( "Step 2 spinner visible" );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( checkoutCartSelector + " .hikashop_checkout_loading_spinner .fa-spin" ) ) ) );
        System.out.println( "Checkout Cart spinner visible" );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "hikashop_checkout_cart_shipping_title" ) ) ) );
        System.out.println( "Spinners are unvisible" );

        String shippingText = driver.findElement( By.id( "hikashop_checkout_cart_shipping_title" ) ).getText();
        assertEquals( shippingText, "Shipping fee" );
        double shippingValue = Double.parseDouble( driver.findElement( By.className( "hikashop_cart_shipping_value" ) ).getText().replace( "$", "" ) );
        System.out.println( "Shipping fee: " +  shippingValue );
        assertEquals( shippingValue, 0.0, 0 );
        eMotorwerksCheckout.orderShippingFee = shippingValue;

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "hikashop_checkout_cart_tax_title" ) ) ) );
        String taxesText = driver.findElement( By.id( "hikashop_checkout_cart_tax_title" ) ).getText();
        assertEquals( taxesText, "Taxes" );
        double taxesValue = Double.parseDouble( driver.findElement( By.className( "hikashop_cart_tax_value" ) ).getText().replace( "$", "" ) );
        System.out.println( "Taxes: " +  taxesValue );
        assertTrue( taxesValue > 0 );
        eMotorwerksCheckout.orderTaxes = taxesValue;

        eMotorwerksCheckout.orderTotal = Double.parseDouble( driver.findElement( By.className( "hikashop_checkout_cart_final_total" ) ).getText().replace( "$", "" ) );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepTwoSelector + ' ' + displayBlockSelector ) ) ) );
    }

    public static void waitForStepThree() {
        WebDriverWait wait = new WebDriverWait( driver, 10 );
        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( stepThreeSelector ) ) ) );
        System.out.println( "Checkout Step 3 is active" );
    }

    public static void validateStripeIsChecked() {
        WebElement stripeCheckbox = driver.findElement( By.id( "payment_radio_2_3__emotorwerksstripejs_10" ) );
        assertTrue( stripeCheckbox.isSelected() );
        System.out.println( "Payment method Credit Card is checked" );
    }

    public static void validateBillingSameAsShippingIsChecked() {
        WebElement billingSameAsShippingCheckbox = driver.findElement( By.id( "helper_b_billing_is_shipping" ) );
        assertTrue( billingSameAsShippingCheckbox.isSelected() );
        System.out.println( "Checkbox Billing address is the same as Shipping is checked" );
    }

    public static void fillStripeFields() {
        int nextYear = Calendar.getInstance().get( Calendar.YEAR ) + 1;
        String nextYearString = String.valueOf( nextYear );
        String lastTwoDigits = nextYearString.substring( nextYearString.length() - 2 );
        WebDriverWait wait = new WebDriverWait( driver, 10 );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.id( "helper_cc_owner" ) ) ) );
        driver.findElement( By.id( "helper_cc_owner" ) ).sendKeys( "Test Test" );

        String originalHandle = driver.getWindowHandle();

        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame4" ) ) );
        driver.findElement( By.name( "cardnumber" ) ).sendKeys( "4111 1111 1111 1111" );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame5" ) ) );
        driver.findElement( By.name( "exp-date" ) ).sendKeys( "12/" + lastTwoDigits );

        driver.switchTo().window( originalHandle );
        driver.switchTo().frame( driver.findElement( By.name( "__privateStripeFrame6" ) ) );
        driver.findElement( By.name( "cvc" ) ).sendKeys( "111" );

        driver.switchTo().window( originalHandle );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikabtn._place-order" ) ) ) );
        driver.findElement( By.cssSelector( ".hikabtn._place-order" ) ).click();

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".hikabtn._place-order .fa.fa-gear.fa-spin" ) ) ) );
        System.out.println( "Place Order button spinner is visible" );

        wait.until( ( ExpectedConditions.visibilityOfElementLocated( By.cssSelector( ".b-shop-checkout__overlay .fa.fa-cog.fa-spin" ) ) ) );
        System.out.println( "Checkout page final spinner is visible" );

        WebDriverWait wait_5 = new WebDriverWait( driver, 5 );
        wait_5.until( ( ExpectedConditions.urlContains( "/cart/checkout/confirm/" ) ) );
        System.out.println( "We are on Thank You Page" );
    }
}
