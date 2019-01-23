import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Scanner;

public class eMotorwerksCheckout {
    public static WebDriver driver;
    public static String productName;
    public static String productDescription;
    public static double productPrice;
    public static int productQuantity;
    public static double orderShippingFee;
    public static double orderTaxes;
    public static double orderTotal;
    public static String clientFirstName = "Serhiy";
    public static String clientSecondName = "Polyukh";
    public static String clientEmail = "geuross@gmail.com";
    public static String clientPhoneNumber = "555-555-5555";
    public static String clientAddress = "154 W 40th Ave";
    public static String clientCity = "San Mateo";
    public static String clientState = "CA";
    public static String clientCountry = "USA";
    public static String clientZipCode = "94403";
    public static String correctShippingName = "UPS Ground";
    public static String correctCountryValue = "country_United_States_of_America_223";
    public static String correctStateValue = "state_California_4265";

    public static void main( String[] args ) {
        Scanner scanner = new Scanner( System.in );
        System.out.print( "Enter chromedriver.exe path (leave empty if it in current directory): " );
        String chromedriverPath = scanner.nextLine().trim();
        System.out.print( "Enter site url (with http(s)://): " );
        String siteUrl = scanner.next().trim();

        String chromedriverFilePath = chromedriverPath + "chromedriver.exe";

        System.out.println( String.format( "Path to chromedriver is %s, site URL is %s", chromedriverFilePath, siteUrl ) );

        System.setProperty( "webdriver.chrome.driver", chromedriverFilePath );

        driver = new ChromeDriver();

        driver.get( siteUrl );
        System.out.println( "Main Page Loaded" );

        driver.manage().window().setSize( new Dimension( 1280, 800 ) );

        MainPage mainPage = new MainPage();
        mainPage.closeCookieBanner();
        mainPage.navigateToCategoryPage();

        ResidentialPage residentialPage = new ResidentialPage();
        residentialPage.addProductToCart();
        residentialPage.waitingForMiniCart();
        residentialPage.goToMiniCart();

        CartPage cartPage = new CartPage();
        cartPage.waitForCheckoutButton();
        cartPage.getData();
        cartPage.goToCheckout();

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.waitForStepOne();
        checkoutPage.fillStepOneFields();
        checkoutPage.waitForDisplayStepOneData();
        checkoutPage.waitForStepTwo();
        checkoutPage.checkCountryAndState();
        checkoutPage.fillStepTwoFields();
        checkoutPage.waitForDisplayStepTwoData();

        checkoutPage.waitForStepThree();
        checkoutPage.validateStripeIsChecked();
        checkoutPage.validateBillingSameAsShippingIsChecked();
        checkoutPage.fillStripeFields();

        ConfirmPage confirmPage = new ConfirmPage();
        confirmPage.checkOrderReview();
        confirmPage.checkOrderPlacedBy();
        confirmPage.checkOrderShippingInfo();
        //driver.quit();
    }

    public static String getTrimmedTextByCssSelector( String selector ) {
        return driver.findElement( By.cssSelector( selector ) ).getText().trim();
    }
}
