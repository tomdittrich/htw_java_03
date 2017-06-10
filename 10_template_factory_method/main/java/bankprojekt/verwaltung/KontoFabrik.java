package bankprojekt.verwaltung;

import bankprojekt.verarbeitung.Konto;
import bankprojekt.verarbeitung.Kunde;

/**
 * Konto Fabrik
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 10.06.17
 */
public abstract class KontoFabrik {

    /**
     * Erstellt ein Konto
     *
     * @param inhaber der Inhaber
     * @param ktn     die Kontonummer
     * @return das Konto
     */
    public abstract Konto erstellen(Kunde inhaber, long ktn);
}
