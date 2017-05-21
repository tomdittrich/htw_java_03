import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Junit Testklasse zur Klasse Nullstelle
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 21.05.17
 */
// Vergleichswerte stammen vom Bisektions Algorithmus von WolframAlpha
public class NullstelleTest {

    @Test
    public void sucheFunktionF() throws Exception {
        // a^2-5
        double ergebnis = Nullstelle.suche((a -> (a * a) - 5), -5, 5);
        assertEquals(2.23, ergebnis, 0.01);
    }

    @Test
    public void sucheFunktionG() throws Exception {
        // e^(3x)-7
        double ergebnis = Nullstelle.suche((a -> Math.exp(3 * a) - 7), -5, 5);
        assertEquals(0.64, ergebnis, 0.01);
    }

    @Test
    public void sucheFunktionH() throws Exception {
        // (5-x)/(x^3 +2)
        double ergebnis = Nullstelle.suche((a -> (5 - a) / (Math.pow(a, 3) + 2)), 0, 10);
        assertEquals(5, ergebnis, 0.01);
    }

}