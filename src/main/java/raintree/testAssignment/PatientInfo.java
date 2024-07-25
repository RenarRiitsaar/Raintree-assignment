package raintree.testAssignment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import raintree.testAssignment.model.Insurance;
import raintree.testAssignment.model.Patient;
import raintree.testAssignment.service.impl.InsuranceServiceImpl;
import raintree.testAssignment.service.impl.PatientServiceImpl;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
public class PatientInfo {

    private static InsuranceServiceImpl insuranceService;
    private static PatientServiceImpl patientService;

    @Autowired
    public PatientInfo(InsuranceServiceImpl insuranceService, PatientServiceImpl patientService) {
        PatientInfo.insuranceService = insuranceService;
        PatientInfo.patientService = patientService;
    }

    public static void displayPatientData() {

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yy");

        try {
            List<Insurance> insuranceList = insuranceService.getAllInsurances();
            insuranceList.sort(Comparator.comparing(Insurance::getFromDate));

            for (Insurance insurance : insuranceList) {

                System.out.println(insurance.getPatientNumber() + ", " +
                        insurance.getPatient().getLast() + ", " +
                        insurance.getPatient().getFirst() + ", " +
                        insurance.getIname() + ", " +
                        sdf.format(insurance.getFromDate()) + ", " +
                        sdf.format(insurance.getToDate()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong with displaying patient information ");
        }
    }

    public static void letterStatistics() {
        List<Insurance> insuranceList = insuranceService.getAllInsurances();
        List<String> patientNames = new ArrayList<>();
        Map<Character, Integer> charMap = new HashMap<>();
        int charCounter = 0;

        try {
            for (Insurance insurance : insuranceList) {
                patientNames.add(insurance.getPatient().getFirst().toLowerCase().trim() +
                        insurance.getPatient().getLast().toLowerCase().trim());
            }
            for (String s : patientNames) {
                for (char c : s.toCharArray()) {
                    charMap.put(c, charMap.getOrDefault(c, 0) + 1);
                    charCounter++;
                }
            }

            for (Map.Entry<Character, Integer> entry : charMap.entrySet()) {
                if (entry.getValue() > 1) {
                    char character = Character.toUpperCase(entry.getKey());
                    int count = entry.getValue();
                    int excludedCharCount = count - 1;
                    double percentage = (count * (double) 100) / charCounter;
                    String formatPercentage = String.format("%.2f", percentage);
                    System.out.println(character + "\t" + excludedCharCount + "\t" + formatPercentage + "%");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong retrieving statistics");
        }
    }

    public static void checkInsuranceWithCurrentDate() {
        List<Patient> patientList = patientService.getAllPatients();

        for (Patient patient : patientList) {

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy");

            patient.displayPatientInsuranceInfo(formatter.format(currentDate));

        }
    }

    public static void dateManualCheck(String input) {
        List<Patient> patientList = patientService.getAllPatients();

        int month = 0;
        int day = 0;
        int year = 0;


        try {
        for (Patient patient : patientList) {
            if(input.length() < 8){
                System.out.println("Invalid input!");
                break;
            }
            if(isInteger(input.substring(0, 2)) && isInteger(input.substring(3, 5)) &&
                    isInteger(input.substring(6, 8)) && input.charAt(2) == '-' && input.charAt(5) == '-') {
                 month = Integer.parseInt(input.substring(0, 2));
                 day = Integer.parseInt(input.substring(3, 5));
                 year = Integer.parseInt(input.substring(6, 8));
            }else{
                System.out.println("Invalid date, reinsert the date!");
                break;
            }

            if (month < 1 || month > 12 || month == 2 && day > 29) {
                System.out.println("The format must be MM-dd-yy, Month " + month + " and day " + day + " was entered!");
                break;
            } else{
               patient.displayPatientInsuranceInfo(input);
                 }
              }
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Something went wrong with date insertion!");
            }

    }
    public static boolean isInteger(String input){
        try{
            Integer.parseInt(input);
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public static void setInsuranceService(InsuranceServiceImpl insuranceService) {
        PatientInfo.insuranceService = insuranceService;
    }

    public static void setPatientService(PatientServiceImpl patientService) {
        PatientInfo.patientService = patientService;
    }
}