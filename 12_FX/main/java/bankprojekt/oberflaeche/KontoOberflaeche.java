package bankprojekt.oberflaeche;

import bankprojekt.verarbeitung.Konto;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Eine Oberfl�che f�r ein einzelnes Konto. Man kann einzahlen
 * und abheben und sperren und die Adresse des Kontoinhabers 
 * �ndern
 * @author Doro
 *
 */
public class KontoOberflaeche extends BorderPane {
	private Text ueberschrift;
	private GridPane anzeige;
	private Text txtNummer;
	/**
	 * Anzeige der Kontonummer
	 */
	private Text nummer;
	private Text txtStand;
	/**
	 * Anzeige des Kontostandes
	 */
	private Text stand;
	private Text txtGesperrt;
	/**
	 * Anzeige und �nderung des Gesperrt-Zustandes
	 */
	private CheckBox gesperrt;
	private Text txtAdresse;
	/**
	 * Anzeige und �nderung der Adresse des Kontoinhabers
	 */
	private TextArea adresse;
	/**
	 * Anzeige von Meldungen �ber Kontoaktionen
	 */
	private Text meldung;
	private HBox aktionen;
	/**
	 * Auswahl des Betrags f�r eine Kontoaktion
	 */
	private Spinner<Double> betrag;
	/**
	 * l�st eine Einzahlung aus
	 */
	private Button einzahlen;
	/**
	 * l�st eine Abhebung aus
	 */
	private Button abheben;

	private Button test;
	
	/**
	 * erstellt die Oberfl�che
	 */
	public KontoOberflaeche(Konto kModel, KontoController kController)
	{
		ueberschrift = new Text("Ein Konto ver�ndern");
		ueberschrift.setFont(new Font("Sans Serif", 25));
		BorderPane.setAlignment(ueberschrift, Pos.CENTER);
		this.setTop(ueberschrift);
		
		anzeige = new GridPane();
		anzeige.setPadding(new Insets(20));
		anzeige.setVgap(10);
		anzeige.setAlignment(Pos.CENTER);
		
		txtNummer = new Text("Kontonummer:");
		txtNummer.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtNummer, 0, 0);
		nummer = new Text(kModel.getKontonummerFormatiert()); // holt sich KTN aus Model
		nummer.setFont(new Font("Sans Serif", 15));
		GridPane.setHalignment(nummer, HPos.RIGHT);
		anzeige.add(nummer, 1, 0);
		
		txtStand = new Text("Kontostand:");
		txtStand.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtStand, 0, 1);
		stand = new Text();
		stand.setFont(new Font("Sans Serif", 15));
		GridPane.setHalignment(stand, HPos.RIGHT);
		anzeige.add(stand, 1, 1);
		
		txtGesperrt = new Text("Gesperrt: ");
		txtGesperrt.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtGesperrt, 0, 2);
		gesperrt = new CheckBox();
		GridPane.setHalignment(gesperrt, HPos.RIGHT);
		anzeige.add(gesperrt, 1, 2);
		
		txtAdresse = new Text("Adresse: ");
		txtAdresse.setFont(new Font("Sans Serif", 15));
		anzeige.add(txtAdresse, 0, 3);
		adresse = new TextArea(kModel.getInhaber().getAdresse());
		adresse.setPrefColumnCount(25);
		adresse.setPrefRowCount(2);
		GridPane.setHalignment(adresse, HPos.RIGHT);
		anzeige.add(adresse, 1, 3);
		
		meldung = new Text("Willkommen lieber Benutzer");
		meldung.setFont(new Font("Sans Serif", 15));
		meldung.setFill(Color.RED);
		anzeige.add(meldung,  0, 4, 2, 1);
		
		this.setCenter(anzeige);
		
		aktionen = new HBox();
		aktionen.setSpacing(10);
		aktionen.setAlignment(Pos.CENTER);

		// eine Zahl muss Double sein, sonst gibt es sp�ter bei einzahlen.setOnAction eine Exception (reimt sich)
		betrag = new Spinner<>(10, 100, 50.0, 10);
		aktionen.getChildren().add(betrag);
		einzahlen = new Button("Einzahlen");
		aktionen.getChildren().add(einzahlen);
		abheben = new Button("Abheben");
		aktionen.getChildren().add(abheben);
		//test = new Button("Test");
		//aktionen.getChildren().add(test);
		
		this.setBottom(aktionen);

		// Testbutton fuer Aenderungen/Funktionspruefung
		// test.setOnAction(e -> kModel.getInhaber().setAdresse("Testadresse"));

		einzahlen.setOnAction(e -> kController.einzahlen(betrag.getValue()));
		abheben.setOnAction(e -> kController.abheben(betrag.getValue()));

		gesperrt.selectedProperty()
				.bindBidirectional(kModel.gesperrtProperty());

		stand.textProperty().bind(kModel.derKontostandProperty()
				.asString().concat( " Euro"));

		adresse.textProperty().bindBidirectional(kModel.getInhaber().adresseProperty());
	}

	void setMeldung(String meldung){
		this.meldung.setText(meldung);
	}
}
