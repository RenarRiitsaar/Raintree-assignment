package raintree.testAssignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import raintree.testAssignment.model.Insurance;
import raintree.testAssignment.model.Patient;
import raintree.testAssignment.service.impl.InsuranceServiceImpl;
import raintree.testAssignment.service.impl.PatientServiceImpl;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class PatientInfoTest {


    @Mock
    private InsuranceServiceImpl insuranceService;

    @Mock
    private PatientServiceImpl patientService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void displayPatientData() throws ParseException {



        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        Patient patient = new Patient(1,"1234","First", "Name", sdf.parse("03-04-1994"));
        Insurance insurance = new Insurance(1,"LHV", sdf.parse("05-12-2024"), sdf.parse("05-12-2025"));
        insurance.setPatient(patient);

        List<Insurance> insuranceList = new ArrayList<>();
         insuranceList.add(insurance);

        PatientInfo.setInsuranceService(this.insuranceService);

        when(insuranceService.getAllInsurances()).thenReturn(insuranceList);


        String expectedOutput = "1234, Name, First, LHV, 05-12-24, 05-12-25";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        PatientInfo.displayPatientData();

        assertEquals(expectedOutput.trim(), actualOutput.toString().trim());
    }

    @Test
    void letterStatistics() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");

        Patient patient = new Patient(1,"1234","John", "Doe", sdf.parse("03-04-1994"));
        Insurance insurance = new Insurance(1,"LHV", sdf.parse("05-12-2024"), sdf.parse("05-12-2025"));
        insurance.setPatient(patient);

        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(insurance);

        PatientInfo.setInsuranceService(this.insuranceService);

        when(insuranceService.getAllInsurances()).thenReturn(insuranceList);


        String expectedOutput = "O\t1\t28.57%";
        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        PatientInfo.letterStatistics();

        assertEquals(expectedOutput.trim(), actualOutput.toString().trim());

    }

    @Test
    void checkInsuranceWithCurrentDate() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");
        LocalDate currentDate = LocalDate.now();

        Patient patient = new Patient(1,"1234","John", "Doe", sdf.parse("03-04-1994"));
        Insurance insurance = new Insurance(1,"LHV", sdf.parse(formatter.format(currentDate)), sdf.parse(formatter.format(currentDate)));
        Insurance insurance2 = new Insurance(1,"SEB", sdf.parse("06-07-23"), sdf.parse("06-08-23"));
        insurance.setPatient(patient);

        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(insurance);
        insuranceList.add(insurance2);

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        patient.setInsuranceList(insuranceList);

        PatientInfo.setPatientService(this.patientService);

        when(insuranceService.getAllInsurances()).thenReturn(insuranceList);
        when(patientService.getAllPatients()).thenReturn(patientList);
        String expectedOutput = "1234, John Doe, LHV, YES" + System.lineSeparator() +
                                "1234, John Doe, SEB, NO";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        PatientInfo.checkInsuranceWithCurrentDate();

        assertEquals(expectedOutput.trim(), actualOutput.toString().trim());

    }

    @Test
    void dateManualCheck() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");

        Patient patient = new Patient(1,"1234","John", "Doe", sdf.parse("03-04-1994"));
        Insurance insurance = new Insurance(1,"SEB", sdf.parse("06-07-23"), sdf.parse("06-08-23"));

        List<Insurance> insuranceList = new ArrayList<>();
        insuranceList.add(insurance);

        List<Patient> patientList = new ArrayList<>();
        patientList.add(patient);
        patient.setInsuranceList(insuranceList);

        PatientInfo.setPatientService(this.patientService);

        when(insuranceService.getAllInsurances()).thenReturn(insuranceList);
        when(patientService.getAllPatients()).thenReturn(patientList);
        String expectedOutput = "1234, John Doe, SEB, YES" + System.lineSeparator()
                                + "1234, John Doe, SEB, NO";

        ByteArrayOutputStream actualOutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actualOutput));
        PatientInfo.dateManualCheck("06-08-23");
        PatientInfo.dateManualCheck("06-09-23");
        assertEquals(expectedOutput.trim(), actualOutput.toString().trim());
    }

    @Test
    void isInteger() {

        String integer = "123";
        String string = "hello world!";

        assertTrue(PatientInfo.isInteger(integer));
        assertFalse(PatientInfo.isInteger(string));
    }
}