package bankprojekt.verarbeitung;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit Testklasse fuer die Klasse Girokonto
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 21.04.17
 */
public class GirokontoTest {

    private Girokonto konto;

    /**
     * Erzeugen von einem Girokonto mit einem Startguthaben von 1000 Euro
     */
    @Before
    public void setUp() {
        konto = new Girokonto();
        konto.einzahlen(1000d);
    }

    /**
     * Test ob die Waehrungswechsel Methode richtig arbeitet
     * Normalfall
     */
    @Test
    public void waehrungswechselTest() {
        konto.waehrungswechsel(Currency.KM);
        assertEquals(Currency.KM, konto.getAktuelleWaehrung());
        assertEquals(1955.83, konto.getKontostand(), 0);
        assertEquals(977.915, konto.getDispo(), 0);

        konto.waehrungswechsel(Currency.EUR);
        assertEquals(Currency.EUR, konto.getAktuelleWaehrung());
        assertEquals(1000, konto.getKontostand(), 0);
        assertEquals(500, konto.getDispo(), 0);
    }

    /**
     * Test ob die Abheben Methode richtig arbeitet, mit Waehrungsangabe
     * Normalfall
     */
    @Test
    public void abhebenTest() throws GesperrtException {
        konto.abheben(500, Currency.KM); // 500 KM = 255.6459406 Euro
        assertEquals((1000 - 255.6459406), konto.getKontostand(), 0.0000001); // 1000 - 255.6459406

        konto.abheben(500, Currency.LTL); // 500 LTL = 144.810009268 Euro
        assertEquals((1000 - 255.6459406 - 144.810009268), konto.getKontostand(), 0.00000001);

    }

    /**
     * Test ob die getAktuelleWaehrung Methode richtig arbeitet
     * Normalfall
     */
    @Test
    public void getAktuelleWaehrungTest() {
        konto.setAktuelleWaehrung(Currency.KM);
        assertEquals(Currency.KM, konto.getAktuelleWaehrung());
    }

    /**
     * Test ob die Einzahlen Methode richtig arbeitet, mit Waehrungsangabe
     * Normalfall
     */
    @Test
    public void einzahlenTest() {
        konto.einzahlen(500, Currency.KM); // 500 KM = 255.6459406 Euro
        assertEquals(1255.6459406, konto.getKontostand(), 0.0000001);
    }

}