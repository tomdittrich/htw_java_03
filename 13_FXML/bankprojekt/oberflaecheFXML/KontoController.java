package bankprojekt.oberflaecheFXML;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * KontoController für die Kontoapp
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 02.07.17
 */
public class KontoController {


    @FXML
    private Girokonto kModel; // erzeugt Girokonto mit default Kunde Max Muster
    @FXML
    private Text kontonummerText;
    @FXML
    private Text meldungText;
    @FXML
    private CheckBox gesperrtCheckBox;
    @FXML
    private Spinner betragSpinner;
    @FXML
    private TextArea adresseTextArea;

    @FXML
    public void initialize() {
        gesperrtCheckBox.selectedProperty().bindBidirectional(kModel.gesperrtProperty());
        adresseTextArea.textProperty().bindBidirectional(kModel.getInhaber().adresseProperty());
    }

    /***
     * Zahlt Geld auf das Konto ein, nimmt Befehl von View entgegen
     *
     */
    public void einzahlen() {
        kModel.einzahlen((Double) betragSpinner.getValue());
        meldungText.setText("Einzahlen erfolgreich");
        //kView.setMeldung("Einzahlen erfolgreich");
    }

    /**
     * hebt Geld vom Konto ab, nimmt Befehl von View entgegen
     */
    public void abheben() {
        try {
            if (kModel.abheben((Double) betragSpinner.getValue())) {
                meldungText.setText("Abheben erfolgreich");
            } else {
                meldungText.setText("Abheben fehlgeschlagen"+System.lineSeparator()+"(Dispo erreicht/Monatsbetrag ueberschritten)");
            }
        } catch (GesperrtException e) {
            meldungText.setText("Konto gesperrt");
        }
    }
}
