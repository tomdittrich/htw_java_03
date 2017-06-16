package uhrwerk;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Stellt eine Digitale uhrwerk dar, die man anhalten und weiterlaufen lassen kann
 *
 */
public class DigitalUhr extends JFrame implements Observer
{
	private static final long serialVersionUID = 1L;
	private static final String TITEL = "Digitaluhr";
	private static final String KNOPF_EIN = "Ein";
	private static final String KNOPF_AUS = "Aus";
	private static final int BREITE = 400;
	private static final int HOEHE = 300;

	private JLabel anzeige;
	private JButton[] knoepfe; // Ein = Einschalten der Anzeige, 
							  // Aus = Ausschalten der Anzeige, 
	
	private boolean uhrAn = true;
	private UhrController uc;
	
	/**
	 * erstellt das Fenster f�r die digitale uhrwerk und bringt es auf den
	 * Bildschirm; zu Beginn l�uft die uhrwerk im 1-Sekunden-Takt
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

		// Erstellen des ActionListeners f�r die beiden Buttons (Ein, Aus):
		ActionListener lauscher = new ActionListener() {
			/**
			 * schaltet je nach gedr�ckten Knopf die Uhrzeitanzeige ein (Ein), die Uhrzeitanzeige
			 * aus (Aus) 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				switch (e.getActionCommand()) {
				case KNOPF_EIN:   //Uhrzeitanzeige anschalten
					uhrAn = true;
					break;
				case KNOPF_AUS:  //Uhrzeitanzeige ausschalten
					uhrAn = false;
					break;
				}
				knoepfe[0].setEnabled(!uhrAn);
				knoepfe[1].setEnabled(uhrAn);
			}
		};

		// Zuf�gen des ActionListeners zu den Buttons
		for (JButton knopf : knoepfe)
		{
			knopf.addActionListener(lauscher);
		}

		// Auf den Bildschirm bringen:
		pack();
		setVisible(true);


	}

	public void uhrEin() {
		this.uhrAn = true;
	}

	public void uhrAus() {
		this.uhrAn = false;
	}

	@Override
	public void update(Observable o, Object arg) {
		Uhr tempUhr = (Uhr) o;
		if (uhrAn)
		{
			anzeige.setText(String.format("%02d:%02d:%02d", tempUhr.getStunde(), tempUhr.getMinute(),
					tempUhr.getSekunde()));
		}

	}
}
