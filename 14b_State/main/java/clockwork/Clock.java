package clockwork;

/**
 * eine Uhr
 */
public class Clock {
    int hr;
    int min;
    private IZustand aktuell;

    /**
     * erzeugt eine Uhr, die die Uhrzeit 0:0 anzeigt
     */
    public Clock() {
        aktuell = new NormalZustand();
        hr = 0;
        min = 0;
    }

    /**
     * Verändert den Zustand der Uhr
     * nach ModeButon
     *
     * @param neu neuer Zustand
     */
    void setZustand(IZustand neu) {
        this.aktuell = neu;
    }

    /**
     * Aktion bei Betätigung von Change Knöpfchen
     */
    public void changeButton() {
        this.aktuell.changeButton(this);
    }

    /**
     * Aktion bei Betätigung von Change Knöpfen
     */
    public void modeButton() {
        this.aktuell.modeButton(this);
    }

    /**
     * gibt die aktuelle Urzeit auf der Konsole aus
     */
    public void showTime() {
        System.out.println("Current time is Hr : " + hr + " Min: " + min);
    }

    /**
     * Uhr um eine Stunde vorstellen
     */
    void changeHour() {
        hr++;
        if (hr == 24)
            hr = 0;
        System.out.println("CHANGE pressed - ");
        showTime();
    }

    /**
     * Uhr um eine Minute vorstellen
     */
    void changeMinute() {
        min++;
        if (min == 60)
            min = 0;
        System.out.println("CHANGE pressed - ");
        showTime();
    }

    /**
     * Anzeige der aktuellen Uhrzeit mit Licht
     */
    void displayTimeWithLight() {
        System.out.println("LIGHT ON: ");
        showTime();
    }
}
