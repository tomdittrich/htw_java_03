package bankprojekt.verarbeitung;

import java.time.LocalDate;

/**
 * ein Sparbuch
 *
 * @author Doro
 */
public class Sparbuch extends Konto {
    /**
     * Monatlich erlaubter Gesamtbetrag f�r Abhebungen
     */
    public static final double ABHEBESUMME = 2000;
    /**
     * Zinssatz, mit dem das Sparbuch verzinst wird. 0,03 entspricht 3%
     */
    private double zinssatz;
    /**
     * Betrag, der im aktuellen Monat bereits abgehoben wurde
     */
    private double bereitsAbgehoben = 0;

    /**
     * Monat und Jahr der letzten Abhebung
     */
    private LocalDate zeitpunkt = LocalDate.now();

    public Sparbuch() {
        zinssatz = 0.03;
    }

    public Sparbuch(Kunde inhaber, long kontonummer) {
        super(inhaber, kontonummer);
        zinssatz = 0.03;
    }

    @Override
    public String toString() {
        String ausgabe = "-- SPARBUCH --" + System.lineSeparator() +
                super.toString()
                + "Zinssatz: " + this.zinssatz * 100 + "%" + System.lineSeparator();
        return ausgabe;
    }

    /**
     * Wechselt die aktuelle Waehrung in die angegebene Waehrung
     *
     * @param neu Waehrung in die gewechselt und umgerechnet werden soll
     */
    @Override
    public void waehrungswechsel(Currency neu) {
        if (this.getAktuelleWaehrung() == Currency.EUR) {
            setKontostand(neu.convert(getKontostand()));
        } else {
            setKontostand((getKontostand() / getAktuelleWaehrung().getRate()) * neu.getRate());
        }

        setAktuelleWaehrung(neu);
    }

    /**
     * LowLevel-Komponente
     * Prueft ob ein Geldabheben für das Sparbuch moeglich ist
     *
     * @param betrag double
     * @return true, wenn Geldabheben moeglich ist
     */
    @Override
    protected boolean abhebenMoeglich(double betrag) {
        LocalDate heute = LocalDate.now();
        if (heute.getMonth() != zeitpunkt.getMonth() || heute.getYear() != zeitpunkt.getYear()) {
            this.bereitsAbgehoben = 0;
            zeitpunkt = heute;
        }

        if (getKontostand() - betrag >= 0.50 &&
                bereitsAbgehoben + betrag <= Sparbuch.ABHEBESUMME) {
            bereitsAbgehoben += betrag;
            return true;
        }

        return false;
    }
}
