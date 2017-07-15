package clockwork;

/**
 * Zustand der Uhr im "Stunden Zustand"
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 15.07.17
 */
public class HoursZustand implements IZustand {

    /**
     * Aktion bei Betätigung von Change Knöpfchen
     * Die Stunde wird um 1 erhöht
     *
     * @param c die Uhr
     */
    @Override
    public void changeButton(Clock c) {
        c.changeHour();
    }

    /**
     * Aktion bei Betätigung von Mode Knöpfchen
     * Wechselt in den nächsten Mode
     *
     * @param c die Uhr
     */
    @Override
    public void modeButton(Clock c) {
        System.out.println("** UPDATING MIN: Press CHANGE button to increase minutes.");
        c.setZustand(new MinZustand());
    }
}
