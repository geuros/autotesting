import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class eMotorwerksCheckout {
    public static WebDriver driver;

    public static void main( String[] args ) {
        System.setProperty( "webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe" );

        driver = new ChromeDriver();

        driver.get( "http://energy.devel/" );

        driver.manage().window().setSize( new Dimension( 1280, 800 ) );

        MainPage mainPage = new MainPage();
        mainPage.closeCookieBaner();
        mainPage.navigateToCategoryPage();

        ResidentialPage residentialPage = new ResidentialPage();
        residentialPage.addProductToCart();
        residentialPage.waitingForMiniCart();
        residentialPage.goToMiniCart();

        CartPage cartPage = new CartPage();
        cartPage.waitForCheckoutButton();
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


        //driver.quit();
    }
}
