import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class ConfirmPage {
    static WebDriver driver = eMotorwerksCheckout.driver;
    static String thankYouPageProductRowSelector = ".b-shop-order__product__row";
    static String thankYouPageOrderTableSelector = ".b-shop-order__details__body table tfoot";
    static String thankYouPageOrderPlacedByBlockSelector = ".b-shop-order__details .row > div:nth-child(2) > div:nth-child(2)";
    static String thankYouPageOrderShippingInfoSelector = ".b-shop-order__details .row > div:nth-child(2) > div:nth-child(4)";

    public static void checkOrderReview() {
        String thankYouPageProductName = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageProductRowSelector + " .hikashop_cart_product_name" );
        String thankYouPageProductDescription = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageProductRowSelector + " .hikashop_cart_product_custom_item_fields" );

        assertEquals( eMotorwerksCheckout.productName, thankYouPageProductName );
        assertEquals( eMotorwerksCheckout.productDescription, thankYouPageProductDescription );
        System.out.println( "Product name is same as in the cart" );

        double thankYouPageProductPrice = Double.parseDouble( eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageProductRowSelector + " .b-shop-order__product__price" ).replace( "$", "" ) );
        int thankYouPageProductQuantity= Integer.parseInt( eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageProductRowSelector + " .b-shop-order__product__qty" ) );
        assertEquals( eMotorwerksCheckout.productPrice, thankYouPageProductPrice, 0 );
        assertEquals( eMotorwerksCheckout.productQuantity, thankYouPageProductQuantity );
        System.out.println( "Product price and quantity is same as in the cart" );

        double thankYouPageOrderShippingFee = Double.parseDouble( eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderTableSelector + " tr:nth-child(2) > td:nth-child(2)" ).replace( "$", "" ) );
        double thankYouPageOrderTaxes = Double.parseDouble( eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderTableSelector + " tr:nth-child(3) > td:nth-child(2)" ).replace( "$", "" ) );
        double thankYouPageOrderTotal = Double.parseDouble( eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderTableSelector + " ._total > td:nth-child(2)" ).replace( "$", "" ) );
        assertEquals( eMotorwerksCheckout.orderShippingFee, thankYouPageOrderShippingFee, 0 );
        assertEquals( eMotorwerksCheckout.orderTaxes, thankYouPageOrderTaxes, 0 );
        assertEquals( eMotorwerksCheckout.orderTotal, thankYouPageOrderTotal, 0 );
        System.out.println( "Order Shipping Fee, Taxes and Total are same as in the cart" );
    }

    public static void checkOrderPlacedBy() {
        String thankYouPageOrderPlacedByNameAndPhone = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderPlacedByBlockSelector + " > p:nth-child(1)" );
        String thankYouPageOrderPlacedByEmail = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderPlacedByBlockSelector + " > p:nth-child(2)" );
        String cartNameAndPhone = eMotorwerksCheckout.clientFirstName + " " + eMotorwerksCheckout.clientSecondName + ", " + eMotorwerksCheckout.clientPhoneNumber;
        assertEquals(  cartNameAndPhone, thankYouPageOrderPlacedByNameAndPhone );
        assertEquals( eMotorwerksCheckout.clientEmail, thankYouPageOrderPlacedByEmail );
        System.out.println( "Order client name, second name, email, and phone are same as in the cart" );
    }

    public static void checkOrderShippingInfo() {
        String thankYouPageOrderClientAddress = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderShippingInfoSelector + " > p:nth-child(1)" );
        String thankYouPageOrderClientCityAndState = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderShippingInfoSelector + " > p:nth-child(2)" );
        String thankYouPageOrderClientCountry = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderShippingInfoSelector + " > p:nth-child(3)" );
        String thankYouPageOrderClientZipCode = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderShippingInfoSelector + " > p:nth-child(4)" );
        String thankYouPageOrderShippingName = eMotorwerksCheckout.getTrimmedTextByCssSelector( thankYouPageOrderShippingInfoSelector + " > p:nth-child(5)" );

        assertEquals(  eMotorwerksCheckout.clientAddress, thankYouPageOrderClientAddress );
        assertEquals(  eMotorwerksCheckout.clientCity + ", " + eMotorwerksCheckout.clientState, thankYouPageOrderClientCityAndState );
        assertEquals(  eMotorwerksCheckout.clientCountry, thankYouPageOrderClientCountry );
        assertEquals(  eMotorwerksCheckout.clientZipCode, thankYouPageOrderClientZipCode );
        assertEquals(  eMotorwerksCheckout.correctShippingName, thankYouPageOrderShippingName );
        System.out.println( "Order shipping info is same as in the cart" );

        System.out.println( "Test complete succesfull!" );
    }
}
