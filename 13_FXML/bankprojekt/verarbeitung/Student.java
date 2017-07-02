package bankprojekt.verarbeitung;

import java.time.LocalDate;

/**
 * Studenten-Kunde einer Bank
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 0.8
 * @date 21.04.17
 */
public class Student extends Kunde {

    /**
     * Name der Hochschule
     */
    private String uniname;

    /**
     * Studienfach
     */
    private String fach;

    /**
     * voraussichtliches Studienende
     */
    private LocalDate studiende;

    /**
     * Semester der letzten Studienbecheinigung
     */
    private int semester;

    /**
     * ob eine aktuelle Studienbescheinigung vorliegt
     */
    private boolean aktuellStudent = false;

    /**
     * Standard Konstrkctor
     */
    public Student() {
        this("Max", "Musterchen", "Holzweg 1", LocalDate.now(), "tolleFH", "Fischzucht", LocalDate.parse("3000-01-01"), 1);
    }

    /**
     * Konstruktor
     *
     * @param vorname   Vorname
     * @param nachname  Nachname
     * @param adresse   Adresse
     * @param gebdat    Geburtsdatum
     * @param uniname   Name der Hochschule
     * @param fach      Studienfach
     * @param studiende Studienende
     * @throws IllegalArgumentException wenn einer der Parameter null ist
     */
    public Student(String vorname, String nachname, String adresse, LocalDate gebdat, String uniname, String fach, LocalDate studiende, int semester) {
        super(vorname, nachname, adresse, gebdat);

        if (uniname == null || fach == null || studiende == null)
            throw new IllegalArgumentException("null als Parameter nich erlaubt");

        if (semester < 0) {
            this.semester = 0;
            this.aktuellStudent = true;
        } else {
            this.semester = semester;
            this.aktuellStudent = false;
        }

        this.uniname = uniname;
        this.fach = fach;
        this.studiende = studiende;
    }

    /**
     * Getter Name der Hochschule
     *
     * @return Name der Hochschule
     */
    public String getUniname() {
        return uniname;
    }

    /**
     * Getter Studienfach
     *
     * @return Studienfach
     */
    public String getFach() {
        return fach;
    }

    /**
     * Getter voraussichtliches Studienende
     *
     * @return Studienende
     */
    public LocalDate getStudiende() {
        return studiende;
    }

    /**
     * Getter Semester der letzten Studienbecheinigung
     *
     * @return Semester der letzten Studienbecheinigung
     */
    public int getSemester() {
        return semester;
    }

    /**
     * Getter ob aktuelle Studienbescheinigung vorliegt
     *
     * @return true = liegt vor
     */
    public boolean getAktuellStudent() {
        return this.aktuellStudent;
    }

    /**
     * Setter fest ob die aktuelle Studienbescheinigung vorliegt
     *
     * @param aktuellStudent true = liegt vor, false = nicht
     */
    public void setAktuellStudent(boolean aktuellStudent) {
        // verguenstigung(aktuellStudent); Platzhalter fuer moegliche Methode
        this.aktuellStudent = aktuellStudent;
    }

    /**
     * Eintragen der aktuellen Studienbescheinigung
     *
     * @param semester das Semester der akt. Bescheinigung
     * @throws IllegalArgumentException wenn Semester gleich oder aelter als das bisherige
     */
    public void neuesSemester(int semester) throws IllegalArgumentException {
        if (semester <= this.semester) {
            throw new IllegalArgumentException("Angegebenes Semester ist gleich oder aelter");
        } else {
            this.semester = semester;
        }
    }

    /**
     * gibt die aktuellen Daten des Students aus
     *
     * @return die Daten
     */
    @Override
    public String toString() {
        String ausgabe;

        ausgabe = super.toString();
        ausgabe += this.uniname + System.getProperty("line.separator");
        ausgabe += this.fach + System.getProperty("line.separator");
        ausgabe += "Studien Ende: " + this.studiende + System.getProperty("line.separator");
        ausgabe += "Semester: " + this.semester + System.getProperty("line.separator");
        ausgabe += "Aktuelle Studienbescheinigung: " + this.aktuellStudent + System.getProperty("line.separator");

        return ausgabe;

    }
}
