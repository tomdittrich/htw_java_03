package uhrwerk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Controller für das Uhrwerk
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 17.06.17
 */
public class UhrController implements ActionListener, KeyListener {

    private Uhr uhr;
    private KreisUhr kuhr;
    private DigitalUhr duhr;

    public UhrController() {
        uhr = new Uhr(); // Model
        duhr = new DigitalUhr(this); // View 1
        kuhr = new KreisUhr(this, uhr); // View 2

        // Views dem zu beobachtenden Model hinzufügen
        uhr.addObserver(duhr);
        uhr.addObserver(kuhr);
    }

    /**
     * Wenn Action (EIN AUS Schalter) in DigitalUhr stattfindet
     *
     * @param e ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case DigitalUhr.KNOPF_EIN:   //Uhrzeitanzeige anschalten
                duhr.uhrEin();
                break;
            case DigitalUhr.KNOPF_AUS:  //Uhrzeitanzeige ausschalten
                duhr.uhrAus();
                break;
        }

        // wechselt die Verfügbarkeit der Knöpfe
        duhr.knoepfeSwitch();
    }

    /**
     * Wenn Tasten innerhalb der KreisUhr gedrückt werden
     *
     * @param e KeyEvent
     */
    @Override
    public void keyPressed(KeyEvent e) {
        switch (Character.toUpperCase(e.getKeyChar())) {
            case 'E':
                kuhr.uhrEin();
                break; // "Ein"
            case 'A':
                kuhr.uhrAus();
                break; // "Aus"
        }
    }

    /**
     * ---- EMPTY ----
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * ---- EMPTY ----
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
