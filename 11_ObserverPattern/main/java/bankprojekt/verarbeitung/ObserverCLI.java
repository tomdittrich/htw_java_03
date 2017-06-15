package bankprojekt.verarbeitung;

/**
 * Kontoaktionen Benachrichtigungen/Observer für die Console
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 15.06.17
 */
public class ObserverCLI implements ObserverKonto {

    Konto konto;

    public ObserverCLI() {

    }

    /**
     * Aufruf bei Kontoaktionen
     *
     * @param konto das betroffene Konto
     */
    @Override
    public void update(Konto konto) {
        this.konto = konto;
        stringToConsole();
    }

    /**
     * Gibt Kontonummer und -stand aus
     */
    protected void stringToConsole() {
        System.out.println(
                "Konto: " + konto.getKontonummer() + System.lineSeparator() +
                        "Kontostand: " + konto.getKontostand() + System.lineSeparator()
        );
    }
}
