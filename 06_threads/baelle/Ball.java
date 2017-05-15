package baelle;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * ein h�pfender Ball
 *
 * @author Doro
 */
public class Ball implements Runnable {
    private static final int XSIZE = 10;
    private static final int YSIZE = 10;
    private JPanel box;
    private int x = 0;
    private int y = 0;
    private int dx = 2;
    private int dy = 2;
    private boolean moves = true;
    /**
     * erstellt einen Ball, der in das angegebene Panel gezeichnet wird
     *
     * @param b Die Zeichenfl�che
     */
    public Ball(JPanel b) {
        box = b;
    }

    /**
     * zeichnet den Ball an seiner aktuellen Position
     */
    public void draw() {
        Graphics g = box.getGraphics();
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    /**
     * l�scht den Ball von der Oberfl�che
     */
    public void loeschen() {
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
    }

    /**
     * bewegt den Ball einen Schritt weiter
     */
    public void move() {
        if (!box.isVisible())
            return;
        Graphics g = box.getGraphics();
        g.setXORMode(box.getBackground());
        g.fillOval(x, y, XSIZE, YSIZE);
        x += dx;
        y += dy;
        Dimension d = box.getSize();
        if (x < 0) {
            x = 0;
            dx = -dx;
        }
        if (x + XSIZE >= d.width) {
            x = d.width - XSIZE;
            dx = -dx;
        }
        if (y < 0) {
            y = 0;
            dy = -dy;
        }
        if (y + YSIZE >= d.height) {
            y = d.height - YSIZE;
            dy = -dy;
        }
        g.fillOval(x, y, XSIZE, YSIZE);
        g.dispose();
    }

    /**
     * bewegt den Ball dauer viele Schritte weiter in der Oberfl�che. Um eine angenehme Animation
     * zu erhalten, wird nach jedem Schritt eine Pause eingelegt.
     *
     * @param dauer Anzahl der Schritte
     */
    public void huepfen(int dauer) {
        this.draw();
        for (int i = 1; i <= dauer; i++) {
            this.move();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        this.loeschen();
    }

    /**
     * Setter Moves Boolean
     *
     * @param moves soll es sich bewegen? true
     */
    public void setMoves(boolean moves) {
        this.moves = moves;
    }

    /**
     * Thread-Run Methode
     * Erzeugt einen Ball mit einer zufälligen Lebensdauer
     */
    @Override
    public void run() {
        Random r = new Random();
        int dauer = r.nextInt(500) + 1000; //Zufallszahl zwischen 1000 und 1500

        // die Dauer wird trotz "anhalten" weiter gezählt
        // ein if-else Block mit i-- wäre eine Schnappsidee
        this.draw();
        for (int i = 1; i <= dauer; i++) {
            if (moves) {
                this.move();
            }
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
            }
        }
        this.loeschen();
    }
}