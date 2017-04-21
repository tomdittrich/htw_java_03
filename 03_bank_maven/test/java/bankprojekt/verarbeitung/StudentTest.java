package bankprojekt.verarbeitung;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * JUnit zur Student Klasse
 *
 * @author Tom Dittrich s0555944@htw-berlin.de
 * @version 1.0
 * @date 21.04.17
 */
public class StudentTest {

    Student student;

    @Before
    public void setUp() {
        student = new Student();
    }

    @Test(expected = IllegalArgumentException.class)
    public void KonstruktorFehlerTest() throws IllegalArgumentException {
        student = new Student(null, null, null, null, null, null, null, 0);
    }

    /**
     * Test ob der Getter Uniname richtig arbeitet
     */
    @Test
    public void getUninameTest() {
        assertEquals("tolleFH", student.getUniname());
    }

    /**
     * Test ob der Getter Studienfach richtig arbeitet
     */
    @Test
    public void getFachTest() {
        assertEquals("Fischzucht", student.getFach());
    }

    /**
     * Test ob der Getter Studienende richtig arbeitet
     */
    @Test
    public void getStudiendeTest() {
        assertEquals(LocalDate.parse("3000-01-01"), student.getStudiende());
    }

    /**
     * Test ob der Getter Semester richtig arbeitet
     */
    @Test
    public void getSemesterTest() {
        assertEquals(1, student.getSemester());
    }

    /**
     * Test ob der Setter aktuelle Bescheinigung richtig arbeitet
     */
    @Test
    public void setAktuellStudentTest() {
        student.setAktuellStudent(false);
        assertFalse(student.getAktuellStudent());
    }

    /**
     * Test ob der Getter aktuelle Bescheinigung richtig arbeitet
     */
    @Test
    public void getAktuellStudentTest() {
        student.setAktuellStudent(true);
        assertTrue(student.getAktuellStudent());
    }

    /**
     * Test die Methode zum setzen eines neuen Semesters richtig arbeitet
     * Normalfall
     */
    @Test
    public void neuesSemesterTest() throws IllegalArgumentException {
        student.neuesSemester(3);
        assertEquals(3, student.getSemester(), 0);
    }

    /**
     * Test die Methode zum setzen eines neuen Semesters richtig arbeitet
     * Fehlerfall
     */
    @Test(expected = IllegalArgumentException.class)
    public void neuesSemesterFehlerTest() throws IllegalArgumentException {
        student.neuesSemester(3);
        student.neuesSemester(2);
    }

}