package baelle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Oberfl�che f�r ein Ballspiel
 *
 * @author Doro
 */
public class BallFrame extends JFrame {
    private static final long serialVersionUID = 1L;

    private Ballspiel spiel;
    private JPanel canvas;
    private JTextField uhrzeit;

    /**
     * erstellt eine Oberfl�che mit 4 Buttons
     */
    public BallFrame() {
        setSize(400, 200);
        setTitle("Tanzende Baelle");
        spiel = new Ballspiel(this);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        Container contentPane = getContentPane();
        uhrzeit = new JTextField();
        contentPane.add(uhrzeit, BorderLayout.NORTH);
        canvas = new JPanel();
        contentPane.add(canvas, BorderLayout.CENTER);
        JPanel p = new JPanel();
        addButton(p, "Ball starten", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                spiel.ballStarten();
            }
        });

        addButton(p, "Anhalten", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                spiel.baelleStoppen();
            }
        });

        addButton(p, "Weiter", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                spiel.baelleWeiter();
            }
        });

        addButton(p, "Leeren", new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                spiel.alleLoeschen();
            }
        });
        contentPane.add(p, BorderLayout.SOUTH);

        // Lambda für die aktuelle Zeit, Thread
        Runnable zeit = () -> {
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
            while (true) {
                uhrzeit.setText(dateFormat.format(Calendar.getInstance().getTime()));
            }
        };
        new Thread(zeit).start();

    }

    /**
     * startet die Ballspiel-Oberfl�che und macht sie sichtbar
     *
     * @param args wird nicht benutzt
     */
    public static void main(String[] args) {
        BallFrame frame = new BallFrame();
        frame.setVisible(true);
    }

    private void addButton(Container c, String title, ActionListener a) {
        JButton b = new JButton(title);
        c.add(b);
        b.addActionListener(a);
    }

    /**
     * liefert die Zeichenfl�che von this
     *
     * @return Zeichenfl�che
     */
    public JPanel getZeichenflaeche() {
        return this.canvas;
    }
}