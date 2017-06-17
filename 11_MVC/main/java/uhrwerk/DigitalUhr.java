package uhrwerk;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Stellt eine Digitale uhr dar, die man anhalten und weiterlaufen lassen kann
 */
public class DigitalUhr extends JFrame implements Observer {
    protected static final String KNOPF_EIN = "Ein";
    protected static final String KNOPF_AUS = "Aus";
    private static final long serialVersionUID = 1L;
    private static final String TITEL = "Digitaluhr";
    private static final int BREITE = 400;
    private static final int HOEHE = 300;

    private JLabel anzeige;
    private JButton[] knoepfe; // Ein = Einschalten der Anzeige, 
    // Aus = Ausschalten der Anzeige,
    private boolean uhrAn = true;
    private UhrController uc;

    /**
     * erstellt das Fenster f�r die digitale uhr und bringt es auf den
     * Bildschirm; zu Beginn l�uft die uhr im 1-Sekunden-Takt
     */
    public DigitalUhr(UhrController u) {
        this.uc = u;
        uhrAn = true;

        // Erstellung der Oberfl�chenelemente:
        setTitle(TITEL);
        setSize(BREITE, HOEHE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(new JLabel(TITEL));
        anzeige = new JLabel();
        add(anzeige);
        knoepfe = new JButton[2];
        knoepfe[0] = new JButton(KNOPF_EIN);
        knoepfe[1] = new JButton(KNOPF_AUS);
        for (JButton knopf : knoepfe) {
            super.add(knopf);
        }
        knoepfe[0].setEnabled(false); // "Ein"

        // Zufuegen des ActionListeners zu den Buttons
        for (JButton knopf : knoepfe) {
            knopf.addActionListener(uc); // UhrController
        }

        // Auf den Bildschirm bringen:
        pack();
        setVisible(true);

    }

    /**
     * Schaltet Uhr Boolean ein
     */
    protected void uhrEin() {
        this.uhrAn = true;
    }

    /**
     * Schaltet Uhr Boolean aus
     */
    protected void uhrAus() {
        this.uhrAn = false;
    }

    /**
     * Wechselt die Verfügbarkeit der Knöpfe
     */
    protected void knoepfeSwitch() {
        knoepfe[0].setEnabled(!uhrAn);
        knoepfe[1].setEnabled(uhrAn);
    }

    /**
     * nach Observable Benachrichtigung
     * aktualisiert die Uhranzeige auf die aktuelle Systemzeit
     *
     * @param o   Uhr Objekt
     * @param arg nix
     */
    @Override
    public void update(Observable o, Object arg) {
        Uhr tempUhr = (Uhr) o;
        if (uhrAn) {
            anzeige.setText(String.format("%02d:%02d:%02d", tempUhr.getStunde(), tempUhr.getMinute(),
                    tempUhr.getSekunde()));
        }

    }
}
