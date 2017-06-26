package bankprojekt.oberflaeche;

import bankprojekt.verarbeitung.GesperrtException;
import bankprojekt.verarbeitung.Girokonto;
import bankprojekt.verarbeitung.Konto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Description
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.1
 * @date 26.06.17
 */
public class KontoController extends Application {

    // erzeugt Girokonto mit default Kunde Max Muster
    private Konto kModel = new Girokonto();
    private  KontoOberflaeche kView;
    private Stage stage;

    @Override
    public void start(Stage primaryStage)
    {
        stage = primaryStage;
        kView = new KontoOberflaeche(kModel, this);
        Scene scene = new Scene(kView, 420, 343);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Girokonto Organizer 3000"); // stunning title
        primaryStage.show();

    }

    void einzahlen(double betrag){
        kModel.einzahlen(betrag);
        kView.setMeldung("Einzahlen erfolgreich");
    }

    void abheben(double betrag) {
        try {
            if(kModel.abheben(betrag)){
                kView.setMeldung("Abheben erfolgreich");
            } else {
                kView.setMeldung("Abheben fehlgeschlagen"+System.lineSeparator()+"(Dispo erreicht/Monatsbetrag ueberschritten)");
            }
        } catch (GesperrtException e) {
            kView.setMeldung("Konto gesperrt");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
