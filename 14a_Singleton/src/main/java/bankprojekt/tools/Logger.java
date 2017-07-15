package bankprojekt.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Loggt Exception Würfe des Programms und legt diese in Textdatei ab
 * (simpler Rohbau)
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.5
 * @date 15.07.17
 */
public class Logger {

    private static Logger logger = new Logger();
    private File file;
    private FileWriter fw;
    private final String PATH = "src/main/java/bankprojekt/tools/logfile.txt";

    /**
     * Konstruktor
     */
    private Logger(){
        file = new File(PATH);
        if (file.exists()){
            System.out.println("Logfile bereits vorhanden");
        } else {
            System.out.println("Logfile nicht vorhanden: wird erzeugt");
        }
    }

    /**
     * Getter von Logger Objekt
     *
     * @return Logger Objekt
     */
    public static Logger getLogger(){
        return logger;
    }

    /**
     * Methode zum Schreiben in die Logdatei
     *
     * @param e die Exception
     */
    // TO DO:
    // Zeit- und Datumsangaben
    // Fehlerbehandlung
    public void writeEntry(Exception e){

        try {
            fw = new FileWriter(file, true);
            fw.write(e.toString() + System.lineSeparator());
            fw.flush();
            fw.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

}
