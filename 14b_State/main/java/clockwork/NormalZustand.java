package clockwork;

/**
 * Zustand der Uhr im "Normal Zustand"
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 15.07.17
 */
public class NormalZustand implements IZustand {

    /**
     * Aktion bei Betätigung von Change Knöpfchen
     * Zeit mit Licht wird angezeigt
     *
     * @param c die Uhr
     */
    @Override
    public void changeButton(Clock c) {
        c.displayTimeWithLight();
    }

    /**
     * Aktion bei Betätigung von Mode Knöpfchen
     * Wechselt in den nächsten Mode
     *
     * @param c die Uhr
     */
    @Override
    public void modeButton(Clock c) {
        System.out.println(" UPDATING HR: Press CHANGE button to increase hours.");
        c.setZustand(new HoursZustand());
    }
}
