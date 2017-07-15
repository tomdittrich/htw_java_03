package clockwork;

/**
 * Interface für die Zustände der Uhr
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 15.07.17
 */
public interface IZustand {

    /**
     * Aktion bei Betätigung von Change Knöpfchen
     *
     * @param c die Uhr
     */
    void changeButton(Clock c);

    /**
     * Aktion bei Betätigung von Mode Knöpfchen
     * Wechselt in den nächsten Mode
     *
     * @param c die Uhr
     */
    void modeButton(Clock c);
}
