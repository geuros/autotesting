import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class eMotorwerksCheckout {

    public static void main(String[] args) {

        System.setProperty( "webdriver.chrome.driver", "C:\\Selenium\\chromedriver.exe" );

        WebDriver driver = new ChromeDriver();

        driver.get( "http://energy.devel/store/residential/juicebox-pro-40-smart-40-amp-evse-with-24-foot-cable" );

        ProductPage productPage = new ProductPage();
        productPage.closeCookieBaner( driver );
        productPage.addProductToCart( driver );
        productPage.waitForSmallCart( driver );
        productPage.goToCart( driver );

        CartPage cartPage = new CartPage();
        cartPage.waitForCheckoutButton( driver );
        cartPage.goToCheckout( driver );

        CheckoutPage checkoutPage = new CheckoutPage();
        checkoutPage.waitForStepOne( driver );
        checkoutPage.fillStepOneFields( driver );
        checkoutPage.waitForStepTwo( driver );
        checkoutPage.fillStepTwoFields( driver );
        checkoutPage.waitForStepThree( driver );
        checkoutPage.waitForStripe( driver );
        checkoutPage.fillStripeFields( driver );

        //ormPage formPage = new FormPage();
        //formPage.submitForm(driver);

        //ConfirmationPage confirmationPage = new ConfirmationPage();
        //confirmationPage.waitForAlertBanner(driver);

        //assertEquals("The form was successfully submitted!", confirmationPage.getAlertBannerText(driver));

        //driver.quit();
    }
}
