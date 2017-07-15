package clockwork;

/**
 * Zustand der Uhr im "Minuten Zustand"
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 15.07.17
 */
public class MinZustand implements IZustand {

    /**
     * Aktion bei Betätigung von Change Knöpfchen
     * die Minute wird um 1 erhöht
     *
     * @param c die Uhr
     */
    @Override
    public void changeButton(Clock c) {
        c.changeMinute();
    }

    /**
     * Aktion bei Betätigung von Mode Knöpfchen
     * Wechselt in den nächsten Mode
     *
     * @param c die Uhr
     */
    @Override
    public void modeButton(Clock c) {
        System.out.println(" Clock is in normal display.");
        c.setZustand(new NormalZustand());
    }
}
