package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

/**
 * JUnit zur Bank klasse, clone() Method
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 02.06.17
 */
public class BankTest {
    Bank b1;
    Bank b2;
    long ktn1, ktn2, ktn3;

    Kunde kundi1 = new Kunde();
    Kunde kundi2 = new Student("Seppel", "Bohnenstange", "Raum 101", LocalDate.parse("2000-01-01"), "Stardust Academy", "Risikopilot", LocalDate.parse("2145-12-12"), 3);
    Kunde kundi3 = new Kunde("Hans", "Grubert", "Terrania", LocalDate.parse("1960-05-05"));

    @Before
    public void setUp() throws Exception {
        b1 = new Bank(17055050);

        ktn1 = b1.girokontoErstellen(kundi1);
        ktn2 = b1.girokontoErstellen(kundi2);
        ktn3 = b1.sparbuchErstellen(kundi3);
    }

    @Test
    public void cloneTest() throws Exception {
        b2 = (Bank) b1.clone();
        b1.geldEinzahlen(ktn1, 400);

        assertEquals(0, b2.getKontostand(ktn1), 0);
        assertEquals(400, b1.getKontostand(ktn1), 0);
    }

}
