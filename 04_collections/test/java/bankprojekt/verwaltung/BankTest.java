package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Student;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit zur Bank Klasse
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 05.05.17
 */
public class BankTest {

    Bank bank;
    long ktn1, ktn2, ktn3;

    Kunde kundi1 = new Kunde();
    Kunde kundi2 = new Student("Seppel", "Bohnenstange", "Raum 101", LocalDate.parse("2000-01-01"), "Stardust Academy", "Risikopilot", LocalDate.parse("2145-12-12"), 3);
    Kunde kundi3 = new Kunde("Hans", "Grubert", "Terrania", LocalDate.parse("1960-05-05"));

    @Before
    public void setUp() throws Exception {
        bank = new Bank(17055050);

        ktn1 = bank.girokontoErstellen(kundi1);
        ktn2 = bank.girokontoErstellen(kundi2);
        ktn3 = bank.sparbuchErstellen(kundi3);
    }

    @Test
    public void getBankleitzahlTest() throws Exception {
        assertEquals(17055050L, bank.getBankleitzahl());
    }

    @Test
    public void getKontostandTest() throws Exception {
        bank.geldEinzahlen(ktn1, 500);
        assertEquals(500.0, bank.getKontostand(ktn1), 0);
    }

    @Test
    public void girokontoErstellenTest() throws Exception {
        long tempKtn = bank.girokontoErstellen(kundi1);
        assertTrue(bank.Kontenliste.containsKey(tempKtn));
    }

    @Test
    public void sparbuchErstellen() throws Exception {
        long tempKtn = bank.sparbuchErstellen(kundi1);
        assertTrue(bank.Kontenliste.containsKey(tempKtn));
    }

    @Test
    public void getAlleKontonummernTest() throws Exception {
        List<Long> tempList = new ArrayList<>();
        tempList.add(ktn1);
        tempList.add(ktn2);
        tempList.add(ktn3);

        assertTrue(tempList.equals(bank.getAlleKontonummern()));
    }

    @Test
    public void geldAbhebenTest() throws Exception {
        assertTrue(bank.geldAbheben(ktn1, 400));
        assertFalse(bank.geldAbheben(ktn1, 600));
    }

    @Test
    public void geldEinzahlenTest() throws Exception {
        bank.geldEinzahlen(ktn2, 500);
        assertEquals(500, bank.getKontostand(ktn2), 0);
    }

    @Test
    public void kontoLoeschenTest() throws Exception {
        bank.kontoLoeschen(ktn3);
        assertFalse(bank.Kontenliste.containsKey(ktn3));
    }

    @Test
    public void geldUeberweisenTest() throws GesperrtException {
        assertTrue(bank.geldUeberweisen(ktn1, ktn2, 500, "normaler Test"));
        assertFalse(bank.geldUeberweisen(ktn1, ktn3, 500, "Girokonto zu Sparbuch Test"));
        assertFalse(bank.geldUeberweisen(ktn1, 1L, 500, "Falsche KTN Test"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void geldUeberweisenTestExceptionNegativerBetrag() throws GesperrtException {
        bank.geldUeberweisen(ktn1, ktn2, -1, "negativer Betrag");
    }

}
