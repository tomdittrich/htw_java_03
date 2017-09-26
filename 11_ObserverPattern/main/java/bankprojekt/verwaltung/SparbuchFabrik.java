package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kunde;
import bankprojekt.verarbeitung.Sparbuch;

/**
 * Sparbuch Fabrik
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 10.06.17
 */
public class SparbuchFabrik extends KontoFabrik {

    /**
     * Erstellt ein Konto
     *
     * @param inhaber der Inhaber
     * @param ktn     die Kontonummer
     * @return das Konto
     */
    @Override
    public Konto erstellen(Kunde inhaber, long ktn) {
        return new Sparbuch(inhaber, ktn);
    }
}
