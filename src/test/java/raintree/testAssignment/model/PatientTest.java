package raintree.testAssignment.model;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class PatientTest {

    @Test
    void getFullName() {
        Patient patient = new Patient();
        patient.setFirst("John");
        patient.setLast("Doe");

        String expected = "John Doe";
        String actual = patient.getFullName();

        assertEquals(expected, actual);
    }

    @Test
    void displayPatientInsuranceInfo() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        Patient patient = new Patient(1,"1234","First", "Name", sdf.parse("03-04-1994"));
        Insurance insurance = new Insurance(1,"LHV", sdf.parse("05-12-2024"), sdf.parse("05-12-2025"));
        patient.addInsurance(insurance);

        String expectedOutput = "1234, First Name, LHV, YES";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        patient.displayPatientInsuranceInfo("12-12-2024");

        assertEquals(expectedOutput.trim(), actualOutput.toString().trim());
    }
}