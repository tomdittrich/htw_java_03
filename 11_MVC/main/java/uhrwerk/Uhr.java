package uhrwerk;

import java.time.LocalTime;
import java.util.Observable;

/**
 * eine Uhr mit Sekundenzaehlung
 */
public class Uhr extends Observable {
    private int stunde, minute, sekunde;

    /**
     * erstellt die Uhr
     */
    public Uhr() {
        //Thread starten, um die Uhrzeit laufen zu lassen:
        new Thread() {
            /**
             * l�st jede Sekunde die Aktualisierung der Uhrzeit aus
             */
            @Override
            public void run() {
                try {
                    while (true) {
                        laufen();
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                }
            }
        }.start();

    }

    /**
     * liefert die aktuelle Stunde
     *
     * @return
     */
    public int getStunde() {
        return stunde;
    }

    /**
     * liefert die aktuelle Minute
     *
     * @return
     */
    public int getMinute() {
        return minute;
    }

    /**
     * liefert die aktuelle Sekunde
     *
     * @return
     */
    public int getSekunde() {
        return sekunde;
    }

    /**
     * Holt die aktuelle System Uhrzeit
     */
    private void laufen() {
        LocalTime jetzt = LocalTime.now();
        stunde = jetzt.getHour();
        minute = jetzt.getMinute();
        sekunde = jetzt.getSecond();

        // Observer benachrichtigen
        setChanged();
        notifyObservers();
    }

}
