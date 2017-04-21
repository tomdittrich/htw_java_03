import bankprojekt.verarbeitung.Currency;
import bankprojekt.verarbeitung.*;

/**
 * Created by Tom on 08.04.2017.
 */
public class Test {
    public static void main(String[] args) {

        Currency currency = Currency.BGN;

        System.out.println(currency.name() +": "+currency.getRate());
        System.out.printf("Umgerechnet sind 3 Euro %f\n", (currency.convert(3)));

        System.out.println(Currency.KM.convert(1)); // als static

        // Aufzählung der Kosntanten
        for(int i = 0; i< Currency.values().length;i++){
            System.out.println(Currency.values()[i]);
        }

        Konto testKonto = new Sparbuch();

    }
}
