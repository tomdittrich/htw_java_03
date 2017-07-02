package bankprojekt.oberflaecheFXML;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Girokonto App
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.9
 * @date 02.07.17
 */

public class Main extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Erzeugt Oberfläche
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("KontoView.fxml"));
        Parent lc = loader.load();
        Scene scene = new Scene(lc);
        primaryStage.setTitle("Organizer 3000+");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
