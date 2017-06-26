package bankprojekt.verarbeitung;

/**
 * Interface für Benachrichtigung/Observer von Kontoaktionen
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 15.06.17
 */
public interface ObserverKonto {

    /**
     * Aufruf bei Kontoaktionen
     *
     * @param konto das betroffene Konto
     */
    public void update(Konto konto);
}
